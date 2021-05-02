package com.neurix.authorization.company;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.StringType;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 21/01/13
 * Time: 9:40
 * To change this template use File | Settings | File Templates.
 */
public class CompanyIdGenerator implements IdentifierGenerator {

    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        Calendar now = Calendar.getInstance();
        SequenceGenerator generator = new SequenceGenerator();
        Properties properties = new Properties();
        properties.put("sequence", "seq_company");
        generator.configure(new StringType(), properties, sessionImplementor.getFactory().getDialect());
        String id=(String)generator.generate(sessionImplementor, o);
        String sId = String.format("%1$tm%1$ty%2$07d", now, id);

        return sId;
    }
}
