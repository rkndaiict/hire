package com.hire.ns.util;

import java.sql.Types;

/**
 * The Class TegrisFireMySQLDialect.
 */
public class NSDialect extends org.hibernate.dialect.Oracle10gDialect {

    /**
     * Instantiates a new tegris fire my sql dialect.
     */
    public NSDialect() {
        super();
        registerColumnType(Types.DECIMAL, "number($p,$s)");
    }
}