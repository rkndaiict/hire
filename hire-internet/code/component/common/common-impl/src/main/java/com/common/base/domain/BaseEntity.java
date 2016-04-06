package com.common.base.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.acls.model.ObjectIdentity;

//@TypeDefs({@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "hibernateStringEncryptor") }),
//		@TypeDef(name = "encryptedACHString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "hibernateACHStringEncryptor") })})
//@FilterDef(name = "exchangeFilter", parameters = { @ParamDef(name = "EXCHANGE_ID", type = "string") })
//@Filters({ @Filter(name = "exchangeFilter", condition = "EXCHANGE_ID = :EXCHANGE_ID") })
@MappedSuperclass
public abstract class BaseEntity implements ObjectIdentity {

	private static final long serialVersionUID = 7620579745392806460L;

	/*
	 * This is added from security context. This value will be populated @ run
	 * time.
	 */
	@Transient
	private String type;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "owner_user_id")
	private Long ownerUserId;

//	@Column(name = "EXCHANGE_ID")
//	private String exchangeId;

	/*
	 * public Long getId() { return this.id; }
	 */

	/**
	 * This method has been made final to prevent overriding by Hibernate Proxy
	 * implementation of lazy initialized entities. This getter method ensures
	 * that for lazy Hibernate entities, proxies are not initialized just when
	 * id accessor method is called because entity identifier can be obtained
	 * without completely loading the lazy entity.
	 * 
	 * @return
	 */
	public final Long getId() {
		if (this instanceof HibernateProxy) {
			return (Long) ((HibernateProxy) this).getHibernateLazyInitializer()
					.getIdentifier();
		}

		return id;

	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the ownerUserIdentifier
	 */
	public Long getOwnerUserId() {
		return this.ownerUserId;
	}

	/**
	 * @param ownerUserId
	 *            the ownerUserIdentifier to set
	 */
	public void setOwnerUserId(Long ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.acls.model.ObjectIdentity#getIdentifier()
	 */
	@Override
	public Serializable getIdentifier() {
		return getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.acls.model.ObjectIdentity#getType()
	 */
	@Override
	public String getType() {
		return this.getClass().getCanonicalName();
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getExchangeId() {
//		return exchangeId;
//	}

//	public void setExchangeId(String exchangeId) {
//		this.exchangeId = exchangeId;
//	}

	// @PrePersist
	// public void setExchangeIdPrePersist() {
	// if(null != ExchangeContextUtil.getExchangeId() &&
	// !StringUtils.hasText(getExchangeId())) //added for multi exchange portals
	// as in that case ExchangeContextUtil will be bull
	// setExchangeId(ExchangeContextUtil.getExchangeId());
	// }
	//
	// @PreUpdate
	// public void onPreUpdate() {
	// if(null != ExchangeContextUtil.getExchangeId() &&
	// !StringUtils.hasText(getExchangeId())) //added for multi exchange portals
	// as in that case ExchangeContextUtil will be bull
	// setExchangeId(ExchangeContextUtil.getExchangeId());
	// }

}
