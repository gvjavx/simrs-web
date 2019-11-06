package com.neurix.common.exception;

import com.neurix.authorization.user.model.ItBusinessObjectLog;
import com.neurix.common.util.CommonUtil;
import org.hibernate.HibernateException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;

/**
 * Created by IntelliJ IDEA.
 * User: Thunderbird
 * Date: 28/02/13
 * Time: 10:07
 * To change this template use File | Settings | File Templates.
 */
public class GenerateBoLog<T> {
    public static <T> Long generateBoLog(T dao, String message, String moduleMethod) throws GeneralBOException {

        Class noParamsForId[] = {};
        Class[] paramForSaveBoLog = new Class[1];
	    paramForSaveBoLog[0] = ItBusinessObjectLog.class;
        Long logId= null;

        try {

            logId = (Long)(dao.getClass().getMethod("getNextBoLogging",noParamsForId)).invoke(dao,null);

            ItBusinessObjectLog businessObjectLog=new ItBusinessObjectLog();
            businessObjectLog.setId(logId);
            businessObjectLog.setModuleMethod(moduleMethod);
            businessObjectLog.setMessage(message);
            businessObjectLog.setBranchId(CommonUtil.userBranchLogin());
            businessObjectLog.setUserId(CommonUtil.userIdLogin());
            businessObjectLog.setErrorTimestamp(new Timestamp(System.currentTimeMillis()));

            dao.getClass().getMethod("addAndSaveBoLog",paramForSaveBoLog).invoke(dao,businessObjectLog);

        } catch (IllegalAccessException e) {
            throw new GeneralBOException("Found problem with data access, please inform to your admin...," + e.getMessage());
        } catch (InvocationTargetException e) {
            throw new GeneralBOException("Found problem with data access, please inform to your admin...," + e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new GeneralBOException("Found problem with data access, please inform to your admin...," + e.getMessage());
        } catch (HibernateException e) {
            throw new GeneralBOException("Found problem with data access, please inform to your admin...," + e.getMessage());
        }

        return logId;
    }
}
