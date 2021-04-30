package com.neurix.authorization.company;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.StringType;

import java.io.Serializable;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 26/01/13
 * Time: 5:33
 * To change this template use File | Settings | File Templates.
 */
public class AreaIdGenerator implements IdentifierGenerator {

    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        SequenceGenerator generator = new SequenceGenerator();
        Properties properties = new Properties();
        properties.put("sequence", "seq_area");
        generator.configure(new StringType(), properties, sessionImplementor.getFactory().getDialect());
        String id=(String)generator.generate(sessionImplementor, o);
        String sId = String.format("%1$04d", id);

        return sId;
    }
}
