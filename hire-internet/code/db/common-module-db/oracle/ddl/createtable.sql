CREATE TABLE changelog (
	change_number INTEGER PRIMARY KEY,
	complete_dt TIMESTAMP DEFAULT (CURRENT_TIMESTAMP) NOT NULL,
	applied_by VARCHAR2(100) DEFAULT (USER) NOT NULL,
    description VARCHAR2(500) NOT NULL
)
/
COMMENT ON TABLE changelog IS 'This table is required by maven dbdeploy plugin.'
/
