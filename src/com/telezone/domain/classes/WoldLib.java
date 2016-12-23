package com.telezone.domain.classes;

public class WoldLib implements java.io.Serializable {

	private Integer recordid;

	private String wordName;

	private String wordValue;

	private String wordType;

	private Integer valueType;

	private Integer maxValue;

	private Integer minValue;

	private Integer maxLines;

	private Integer module;

	private String language;

	private Integer ifUpdate;

	private String wordClass;

	private Integer isSub;

	public Integer getRecordid() {
		return this.recordid;
	}

	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}

	public String getWordName() {
		return this.wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getWordValue() {
		return this.wordValue;
	}

	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}

	public String getWordType() {
		return this.wordType;
	}

	public void setWordType(String wordType) {
		this.wordType = wordType;
	}

	public Integer getValueType() {
		return this.valueType;
	}

	public void setValueType(Integer valueType) {
		this.valueType = valueType;
	}

	public Integer getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		return this.minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public Integer getMaxLines() {
		return this.maxLines;
	}

	public void setMaxLines(Integer maxLines) {
		this.maxLines = maxLines;
	}

	public Integer getModule() {
		return this.module;
	}

	public void setModule(Integer module) {
		this.module = module;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getIfUpdate() {
		return this.ifUpdate;
	}

	public void setIfUpdate(Integer ifUpdate) {
		this.ifUpdate = ifUpdate;
	}

	public String getWordClass() {
		return this.wordClass;
	}

	public void setWordClass(String wordClass) {
		this.wordClass = wordClass;
	}

	public Integer getIsSub() {
		return this.isSub;
	}

	public void setIsSub(Integer isSub) {
		this.isSub = isSub;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WoldLib))
			return false;
		WoldLib castOther = (WoldLib) other;

		return ((this.getRecordid() == castOther.getRecordid()) || (this
				.getRecordid() != null
				&& castOther.getRecordid() != null && this.getRecordid()
				.equals(castOther.getRecordid())))
				&& ((this.getWordName() == castOther.getWordName()) || (this
						.getWordName() != null
						&& castOther.getWordName() != null && this
						.getWordName().equals(castOther.getWordName())))
				&& ((this.getWordValue() == castOther.getWordValue()) || (this
						.getWordValue() != null
						&& castOther.getWordValue() != null && this
						.getWordValue().equals(castOther.getWordValue())))
				&& ((this.getWordType() == castOther.getWordType()) || (this
						.getWordType() != null
						&& castOther.getWordType() != null && this
						.getWordType().equals(castOther.getWordType())))
				&& ((this.getValueType() == castOther.getValueType()) || (this
						.getValueType() != null
						&& castOther.getValueType() != null && this
						.getValueType().equals(castOther.getValueType())))
				&& ((this.getMaxValue() == castOther.getMaxValue()) || (this
						.getMaxValue() != null
						&& castOther.getMaxValue() != null && this
						.getMaxValue().equals(castOther.getMaxValue())))
				&& ((this.getMinValue() == castOther.getMinValue()) || (this
						.getMinValue() != null
						&& castOther.getMinValue() != null && this
						.getMinValue().equals(castOther.getMinValue())))
				&& ((this.getMaxLines() == castOther.getMaxLines()) || (this
						.getMaxLines() != null
						&& castOther.getMaxLines() != null && this
						.getMaxLines().equals(castOther.getMaxLines())))
				&& ((this.getModule() == castOther.getModule()) || (this
						.getModule() != null
						&& castOther.getModule() != null && this.getModule()
						.equals(castOther.getModule())))
				&& ((this.getLanguage() == castOther.getLanguage()) || (this
						.getLanguage() != null
						&& castOther.getLanguage() != null && this
						.getLanguage().equals(castOther.getLanguage())))
				&& ((this.getIfUpdate() == castOther.getIfUpdate()) || (this
						.getIfUpdate() != null
						&& castOther.getIfUpdate() != null && this
						.getIfUpdate().equals(castOther.getIfUpdate())))
				&& ((this.getWordClass() == castOther.getWordClass()) || (this
						.getWordClass() != null
						&& castOther.getWordClass() != null && this
						.getWordClass().equals(castOther.getWordClass())))
				&& ((this.getIsSub() == castOther.getIsSub()) || (this
						.getIsSub() != null
						&& castOther.getIsSub() != null && this.getIsSub()
						.equals(castOther.getIsSub())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRecordid() == null ? 0 : this.getRecordid().hashCode());
		result = 37 * result
				+ (getWordName() == null ? 0 : this.getWordName().hashCode());
		result = 37 * result
				+ (getWordValue() == null ? 0 : this.getWordValue().hashCode());
		result = 37 * result
				+ (getWordType() == null ? 0 : this.getWordType().hashCode());
		result = 37 * result
				+ (getValueType() == null ? 0 : this.getValueType().hashCode());
		result = 37 * result
				+ (getMaxValue() == null ? 0 : this.getMaxValue().hashCode());
		result = 37 * result
				+ (getMinValue() == null ? 0 : this.getMinValue().hashCode());
		result = 37 * result
				+ (getMaxLines() == null ? 0 : this.getMaxLines().hashCode());
		result = 37 * result
				+ (getModule() == null ? 0 : this.getModule().hashCode());
		result = 37 * result
				+ (getLanguage() == null ? 0 : this.getLanguage().hashCode());
		result = 37 * result
				+ (getIfUpdate() == null ? 0 : this.getIfUpdate().hashCode());
		result = 37 * result
				+ (getWordClass() == null ? 0 : this.getWordClass().hashCode());
		result = 37 * result
				+ (getIsSub() == null ? 0 : this.getIsSub().hashCode());
		return result;
	}

}