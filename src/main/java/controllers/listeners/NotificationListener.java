package controllers.listeners;

import common.utils.Mailer;
import models.pojo.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by yuri on 28.02.17.
 */
public class NotificationListener implements HttpSessionAttributeListener {
    private static Logger logger = Logger.getLogger(NotificationListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        logger.trace("NotificationListener attribute added " + event.getName());

//        mailer(event);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.trace("NotificationListener attribute replaced" + event.getName());

//        mailer(event);
    }

    private void mailer(HttpSessionBindingEvent event){
        if ("notification".equals(event.getName())) {
            logger.trace("NotificationListener event name == notification");
            User user = (User) event.getValue();

            if(user.isNotification()){
                logger.trace("NotificationListener init send mail to admin");

                String toEmail = user.getEmail();
                String subject = "ИС Ребусы и загаддки";
                String mes = "Произведен вход админа сервиса под логином - " + user.getLogin();

                Mailer.sendMail(subject, mes, toEmail);
            }
        }
    }

}
