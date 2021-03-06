package org.cloudfoundry.promregator.cfaccessor;

public class CacheKeyApplication {
	private String orgId;
	private String spaceId;
	private String applicationName;
	
	public CacheKeyApplication(String orgId, String spaceId, String applicationName) {
		super();
		this.orgId = orgId;
		this.spaceId = spaceId;
		this.applicationName = applicationName;
	}
	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}
	/**
	 * @return the spaceId
	 */
	public String getSpaceId() {
		return spaceId;
	}
	/**
	 * @return the applicationName
	 */
	public String getApplicationName() {
		return applicationName;
	}
	
	/* (non-Javadoc)
	 * Auto-generated by Elicpse
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((spaceId == null) ? 0 : spaceId.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * Auto-generated by Elicpse
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheKeyApplication other = (CacheKeyApplication) obj;
		if (applicationName == null) {
			if (other.applicationName != null)
				return false;
		} else if (!applicationName.equals(other.applicationName))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (spaceId == null) {
			if (other.spaceId != null)
				return false;
		} else if (!spaceId.equals(other.spaceId))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CacheKeyApplication [orgId=" + orgId + ", spaceId=" + spaceId + ", applicationName=" + applicationName
				+ "]";
	}
	
}
