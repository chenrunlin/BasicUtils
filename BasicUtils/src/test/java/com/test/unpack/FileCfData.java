package com.test.unpack;

import com.bsit.unpack.CodeType;
import com.bsit.unpack.ModelAnnotation;

public class FileCfData {
	
	private String id;
	
	@ModelAnnotation(placeholder = 1, length = 12, CODE_TYPE = CodeType.ASCII, desc = "清分结算机构流水号,取值范围000000000001～999999999999")
	private String cleanSeq;
	
	@ModelAnnotation(placeholder = 2, length = 20, CODE_TYPE = CodeType.ASCII, desc = "交易流水号,取值范围00000000000000000001～99999999999999999999")
	private String transSeq;
	
	@ModelAnnotation(placeholder = 3, length = 12, CODE_TYPE = CodeType.ASCII, desc = "终端编号")
	private String termNo;
	
	@ModelAnnotation(placeholder = 4, length = 16, CODE_TYPE = CodeType.ASCII, desc = "发卡机构代码")
	private String issOrgCode;
	
	@ModelAnnotation(placeholder = 5, length = 2, CODE_TYPE = CodeType.ASCII, desc = "交易状态")
	private String transStatus;
	
	@ModelAnnotation(placeholder = 6, length = 14, CODE_TYPE = CodeType.ASCII, desc = "交易时间")
	private String transTime;
	
	@ModelAnnotation(placeholder = 7, length = 3, CODE_TYPE = CodeType.ASCII, desc = "线路ID")
	private String lineId;
	
	@ModelAnnotation(placeholder = 8, length = 50, CODE_TYPE = CodeType.ASCII, desc = "线路名称")
	private String lineName;
	
	@ModelAnnotation(placeholder = 9, length = 20, CODE_TYPE = CodeType.ASCII, desc = "银联卡号")
	private String cardNo;
	
	@ModelAnnotation(placeholder = 10, length = 2, CODE_TYPE = CodeType.ASCII, desc = "卡类型")
	private String cardType;
	
	@ModelAnnotation(placeholder = 11, length = 4, CODE_TYPE = CodeType.ASCII, desc = "交易地城市代码")
	private String transCityCode;
	
	@ModelAnnotation(placeholder = 12, length = 4, CODE_TYPE = CodeType.ASCII, desc = "发卡地城市代码")
	private String issCityCode;
	
	@ModelAnnotation(placeholder = 13, length = 8, CODE_TYPE = CodeType.ASCII, desc = "交易金额")
	private String transAmount;
	
	@ModelAnnotation(placeholder = 14, length = 8, CODE_TYPE = CodeType.ASCII, desc = "实收金额")
	private String actualAmount;
	
	@ModelAnnotation(placeholder = 15, length = 3, CODE_TYPE = CodeType.ASCII, desc = "货币代码")
	private String currencyCode;
	
	@ModelAnnotation(placeholder = 16, length = 4, CODE_TYPE = CodeType.ASCII, desc = "进站站点ID")
	private String inStationId;
	
	@ModelAnnotation(placeholder = 17, length = 4, CODE_TYPE = CodeType.ASCII, desc = "出站站点ID")
	private String outStationId;
	
	@ModelAnnotation(placeholder = 18, length = 2, CODE_TYPE = CodeType.ASCII, 
			desc = "业务类型:01：二维码云码开户,02：HCE云卡开户,03：二维码云码用户消费（乘车）,04：HCE云卡账户空中充值,05：HCE云卡账户空中充值冲正,06：银联联机交易,07：银联ODA交易")
	private String businessType;
	
	@ModelAnnotation(placeholder = 19, length = 8, CODE_TYPE = CodeType.ASCII, desc = "传输日期:YYYYmmdd")
	private String transmissionDate;

	private String saveTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCleanSeq() {
		return cleanSeq;
	}

	public void setCleanSeq(String cleanSeq) {
		this.cleanSeq = cleanSeq;
	}

	public String getTransSeq() {
		return transSeq;
	}

	public void setTransSeq(String transSeq) {
		this.transSeq = transSeq;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getIssOrgCode() {
		return issOrgCode;
	}

	public void setIssOrgCode(String issOrgCode) {
		this.issOrgCode = issOrgCode;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
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

	public String getTransCityCode() {
		return transCityCode;
	}

	public void setTransCityCode(String transCityCode) {
		this.transCityCode = transCityCode;
	}

	public String getIssCityCode() {
		return issCityCode;
	}

	public void setIssCityCode(String issCityCode) {
		this.issCityCode = issCityCode;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getInStationId() {
		return inStationId;
	}

	public void setInStationId(String inStationId) {
		this.inStationId = inStationId;
	}

	public String getOutStationId() {
		return outStationId;
	}

	public void setOutStationId(String outStationId) {
		this.outStationId = outStationId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getTransmissionDate() {
		return transmissionDate;
	}

	public void setTransmissionDate(String transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	public String getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	@Override
	public String toString() {
		return "FileCfData [cleanSeq=" + cleanSeq + ", transSeq=" + transSeq
				+ ", transmissionDate=" + transmissionDate + "]";
	}


}
