package ua.org.gostroy.communityJavaProject.rmi.cxf.client;


import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import javax.wsdl.Port;
import javax.wsdl.Service;

/**
 * Created by Sergey on 9/6/2015.
 */
public class ClientTest {

    public void initClient(){

        //Создаем сервис
        Service service = new Service();
        //Получаем порт
        Port port = service.getPort();

        /*На время отладки, а иногда и в бою, необходимо логировать запросы и ответы веб-сервиса*/
        //Создаем интерцептор для логирования исходящих сообщений
        LoggingOutInterceptor loi = new LoggingOutInterceptor();
        //Создаем интерцептор для логирования входящих сообщений
        LoggingInInterceptor lii = new LoggingInInterceptor();
        //Регистрируем интерцепторы ClientProxy.getClient(port).getEndpoint().getInInterceptors().add(lii);
        ClientProxy.getClient(port).getEndpoint().getOutInterceptors().add(loi);
        ClientProxy.getClient(port).getEndpoint().getOutFaultInterceptors().add(loi);
    }
}
