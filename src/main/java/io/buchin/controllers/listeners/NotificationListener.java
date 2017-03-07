package io.buchin.controllers.listeners;

import io.buchin.common.utils.Mailer;
import io.buchin.models.pojo.User;
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
        logger.trace("NotificationListener attribute added " + event.getName() + " - " + event.getValue());

        mailer(event);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.trace("NotificationListener attribute removed " + event.getName() + " - " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.trace("NotificationListener attribute replaced " + event.getName() + " - " + event.getValue());

//        mailer(event);
    }

    private void mailer(HttpSessionBindingEvent event){
        if ("mailTo".equals(event.getName())) {
            logger.trace("NotificationListener event name == mailTo");
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
