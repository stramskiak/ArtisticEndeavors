CREATE TABLE VISITOR (
  ID           INT    NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
,  USERNAME     VARCHAR(20) NOT NULL CONSTRAINT customer_usrnm_UC UNIQUE
,  PASSWORD     VARCHAR(20) ,  FIRSTNAME    VARCHAR(50)
,  LASTNAME     VARCHAR(50) ,  EMAIL     VARCHAR(50)
,  ACTIVE_SINCE DATE
);

CREATE TABLE LISTITEM (
  ID INT   NOT NULL GENERATED by default AS IDENTITY PRIMARY KEY
,  VISITOR_ID INT NOT NULL CONSTRAINT LIST_VISIT_FK REFERENCES VISITOR
,  ITEM_ID     VARCHAR(20) NOT NULL
);