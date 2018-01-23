package com.test.bus_server;

public class TConsume {
	
	private String id;
	private String unitNo;			//清算单元编号
	private String termNo;			//终端编号
	private String lineNo;			//线路编号
	private String driverNo;		//司机编号
	private String busNo;			//车辆编号，默认：000000000000 
	private String classSeq;		//班次序列号，默认：000000000000
	private String issuerOrgTag;	//发卡机构代码
	private String cityCode;		//城市代码
	private String cardNo;			//卡应用序列号
	private String cardType;		//卡应用类型
	private String transSeq;		//交易序号
	private String transAmount;		//交易金额
	private String transType;		//交易类型：06：消费  
	private String termTransSeq;	//终端交易序号
	private String transDateTime;	//交易日期时间：yyyyMMddHHmmss
	private String transTac;		//交易验证码
	private String cardNowBalance;	//卡片当前余额，单位为“分”
	private String cardPreBalance;	//卡片交易前余额，单位为“分”
	private String createTime;		//插入时间
	private String isUpload;		//是否上传
	private String errorCode;		//错误代码
	private String errorInfo;		//错误信息
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getClassSeq() {
		return classSeq;
	}
	public void setClassSeq(String classSeq) {
		this.classSeq = classSeq;
	}
	public String getIssuerOrgTag() {
		return issuerOrgTag;
	}
	public void setIssuerOrgTag(String issuerOrgTag) {
		this.issuerOrgTag = issuerOrgTag;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getTransSeq() {
		return transSeq;
	}
	public void setTransSeq(String transSeq) {
		this.transSeq = transSeq;
	}
	public String getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTermTransSeq() {
		return termTransSeq;
	}
	public void setTermTransSeq(String termTransSeq) {
		this.termTransSeq = termTransSeq;
	}
	public String getTransDateTime() {
		return transDateTime;
	}
	public void setTransDateTime(String transDateTime) {
		this.transDateTime = transDateTime;
	}
	public String getTransTac() {
		return transTac;
	}
	public void setTransTac(String transTac) {
		this.transTac = transTac;
	}
	public String getCardNowBalance() {
		return cardNowBalance;
	}
	public void setCardNowBalance(String cardNowBalance) {
		this.cardNowBalance = cardNowBalance;
	}
	public String getCardPreBalance() {
		return cardPreBalance;
	}
	public void setCardPreBalance(String cardPreBalance) {
		this.cardPreBalance = cardPreBalance;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	
}
