package com.bsit.unpack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * TLV 报文解析的工具类
 *
 * @author yt.yin
 * @version 1.0 2017-08-15  23:25:05
 */
public class DataResolve {

    /**
     * 解析报文封装成对象
     *
     * @param bytes    要解析的字节数组
     * @param javaBean 要封装的java对象
     */
    public static <T> T resolve(byte[] bytes, T javaBean) throws Exception {
        if (bytes == null || bytes.length < 1) {
            return javaBean;
        }
        // 先获得到 fieldMap
        Map<String, ModelAnnotation> fieldMap = getFieldsNameAndAnnotation(javaBean);
        // 校验 placeholder 是否否和要求,不符合要求直接抛出异常
        boolean flag = checkPlaceholder(fieldMap);
        if (!flag) {
            throw new Exception("注解 placeholder 的值配置有误！请检查 placeholder 的值是否连续且不重复！");
        }
        // 获取 key 是 palceholder ，value 是 ModelAnnotation 的map
        Map<Integer, ModelAnnotation> modelAnnotationMap = getModelAnnotationMap(fieldMap);

        // 然后截取好每个字段的字节数组，存到一个 map 集合中
        Map<String, byte[]> fieldNameAndByteArray = new HashMap<String, byte[]>();
        Set<Map.Entry<String, ModelAnnotation>> entries = fieldMap.entrySet();
        for (Map.Entry<String, ModelAnnotation> entry : entries) {
            String fieldName = entry.getKey();
            ModelAnnotation annotation = entry.getValue();
            // 获取当前字段截取的开始位置
            int index = getIndex(fieldName, fieldMap, modelAnnotationMap);
            int length = annotation.length();
            byte[] fieldValueByteArray = new byte[length];
            for (int i = index; i < index + length; i++) {
                fieldValueByteArray[i - index] = bytes[i];
            }
            fieldNameAndByteArray.put(fieldName, fieldValueByteArray);
        }
        // 根据配的字节类型解析字节数组封装到对象
        packagingJavaBean(javaBean, fieldMap, fieldNameAndByteArray);
        return javaBean;
    }

    /**
     * 将每个字段对应的字节数组，封装到对象中
     *
     * @param javaBean
     * @param fieldMap
     * @param fieldNameAndByteArray
     */
    private static <T> void packagingJavaBean(T javaBean, Map<String, ModelAnnotation> fieldMap, Map<String, byte[]> fieldNameAndByteArray) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = javaBean.getClass();
        Set<Map.Entry<String, ModelAnnotation>> entries = fieldMap.entrySet();
        for (Map.Entry<String, ModelAnnotation> entry : entries) {
            String fieldName = entry.getKey();
            ModelAnnotation annotation = entry.getValue();
            CodeType codeType = annotation.CODE_TYPE();
            String methodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            if (codeType == CodeType.ASCII){
                Method method = clazz.getMethod(methodName, String.class);
                byte [] bytes = fieldNameAndByteArray.get(fieldName);
                String value = new String(bytes);
                method.invoke(javaBean, value);
            }else if (codeType == CodeType.BYTEARRAY){
                Method method = clazz.getMethod(methodName, byte[].class);
                byte [] bytes = fieldNameAndByteArray.get(fieldName);
                method.invoke(javaBean, bytes);
            }else if (codeType == CodeType.BCD){
                Method method = clazz.getMethod(methodName, String.class);
                byte [] bytes = fieldNameAndByteArray.get(fieldName);
                String value = CodeConvertUtils.BCD_To_Str(bytes);
                method.invoke(javaBean, value);
            }
        }
    }

    /**
     * 获得字段名和字段值
     *
     * @param javaBean
     * @return 返回字段名和注解对应的 map 集合
     */
    private static <T> Map<String, ModelAnnotation> getFieldsNameAndAnnotation(T javaBean) {
        // 获取到 class 对象
        Class<?> clazz = javaBean.getClass();
        // 拿到所有的字段
        Field[] fields = clazz.getDeclaredFields();
        // 对所有属性进行遍历，拿到带注解的属性,然后存到一个map集合中
        Map<String, ModelAnnotation> fieldMap = new HashMap<String, ModelAnnotation>();
        for (Field field : fields) {
            // 判断当前字段上是否有注解
            boolean flag = field.isAnnotationPresent(ModelAnnotation.class);
            if (!flag) {
                continue;
            }
            ModelAnnotation modelAnnotation = field.getAnnotation(ModelAnnotation.class);
            String fieldName = field.getName();
            fieldMap.put(fieldName, modelAnnotation);
        }
        return fieldMap;
    }

    /**
     * 校验 placeholder 的值是否连续
     *
     * @param fieldMap
     * @return 连续符合要求 返回 true, 不符合返回 false
     */
    private static boolean checkPlaceholder(Map<String, ModelAnnotation> fieldMap) {
        if (fieldMap == null || fieldMap.isEmpty()) {
            return false;
        }
        // 定义一个集合用来存放placeholder的值
        List<Integer> placeholders = new ArrayList<Integer>();
        Set<Map.Entry<String, ModelAnnotation>> entries = fieldMap.entrySet();
        for (Map.Entry<String, ModelAnnotation> entry : entries) {
            Integer placeholder = entry.getValue().placeholder();
            placeholders.add(placeholder);
        }
        // 对 placeholder 进行排序
        Collections.sort(placeholders);
        for (int i = 0; i < placeholders.size() - 1; i++) {
            if (placeholders.get(i + 1) - placeholders.get(i) != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成一个 key 是 palceholder ，value 是 ModelAnnotation 的map
     *
     * @param fieldMap
     * @return
     */
    private static Map<Integer, ModelAnnotation> getModelAnnotationMap(Map<String, ModelAnnotation> fieldMap) {
        if (fieldMap == null || fieldMap.isEmpty()) {
            return new HashMap<Integer, ModelAnnotation>();
        }
        Map<Integer, ModelAnnotation> ModelAnnotationMap = new HashMap<Integer, ModelAnnotation>();
        Set<Map.Entry<String, ModelAnnotation>> entries = fieldMap.entrySet();
        for (Map.Entry<String, ModelAnnotation> entry : entries) {
            ModelAnnotation modelAnnotation = entry.getValue();
            ModelAnnotationMap.put(modelAnnotation.placeholder(), modelAnnotation);
        }
        return ModelAnnotationMap;
    }

    /**
     * 获取当前字段要截取的位置
     *
     * @param fieldMap
     * @param fieldName
     * @return startIndex 开始截取的位置索引
     */
    private static int getIndex(String fieldName, Map<String, ModelAnnotation> fieldMap, Map<Integer, ModelAnnotation> modelAnnotationMap) {
        if (fieldName == null || "".equals(fieldName)) {
            return -1;
        }
        ModelAnnotation modelAnnotation = fieldMap.get(fieldName);
        // 获得当前字段的 palceholder 的值
        Integer currentPalceholder = modelAnnotation.placeholder();
        if (currentPalceholder == 1) {
            return 0;
        }
        Integer startIndex = 0;
        for (Integer i = 1; i < currentPalceholder; i++) {
            modelAnnotation = modelAnnotationMap.get(i);
            startIndex += modelAnnotation.length();
        }
        return startIndex;
    }

}
