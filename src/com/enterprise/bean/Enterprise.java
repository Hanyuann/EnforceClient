package com.enterprise.bean;

public class Enterprise {
	private String mc;
	private String address;
	private String business;
	private String biaozhun;
	private String source;
	private String status;
	private String createTime;
	private String lastModifyTime;

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBiaozhun() {
		return biaozhun;
	}

	public void setBiaozhun(String biaozhun) {
		this.biaozhun = biaozhun;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	public String toString() {
		return "Enterprise [mc=" + mc + ", address=" + address + ", business="
				+ business + ", biaozhun=" + biaozhun + ", source=" + source
				+ ", status=" + status + ", createTime=" + createTime
				+ ", lastModifyTime=" + lastModifyTime + "]";
	}

}