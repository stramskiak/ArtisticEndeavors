From: http://wiki.metawerx.net/wiki/Web.xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/j2ee"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
     http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
     version="2.5">

</web-app>
Inside the <web-app> tag, a number of other elements can be added. These are detailed below.

Detailed Reference - click a tag for more information
<?xml version="1.0" encoding="UTF-8"?> 
<web-app xmlns="http://java.sun.com/xml/ns/j2ee" 
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
        xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee 
        http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" 
        version="2.4"> 

        <!-- ========================================================== --> 
        <!-- General --> 
        <!-- ========================================================== --> 

        <!-- Name the application --> 
        <display-name>Example App</display-name> 
        <description>An example application which is used to play with some of the features of Tomcat</description> 

        <!-- This app is cluster-ready --> 
        <distributable /> 

        <!-- Set timeout to 120 minutes --> 
        <session-config> 
                <session-timeout>120</session-timeout> 
        </session-config> 


        <!-- ========================================================== --> 
        <!-- Custom Tag Libraries --> 
        <!-- ========================================================== --> 

        <!-- Taglib declarations are no longer required since JSP 2.0, see Removing taglib from web.xml --> 
        <!-- The <taglib> did not need to be a child of <jsp-config> in earlier versions but is required as of Tomcat 7 --> 
        <!-- Note that you can only have one <jsp-config> element per web.xml --> 
        <!-- 
        <jsp-config> 
                <taglib> 
                        <taglib-uri>mytags</taglib-uri> 
                        <taglib-location>/WEB-INF/jsp/mytaglib.tld</taglib-location> 
                </taglib> 
        </jsp-config> 
        --> 


        <!-- ========================================================== --> 
        <!-- JSP Configuration --> 
        <!-- ========================================================== --> 

        <jsp-config> 
                <jsp-property-group> 
                        <url-pattern>*.jsp</url-pattern> 
                        <include-prelude>/WEB-INF/jspf/prelude1.jspf</include-prelude> 
                        <include-coda>/WEB-INF/jspf/coda1.jspf</include-coda> 
                </jsp-property-group> 
        </jsp-config> 


        <!-- ========================================================== --> 
        <!-- Context Parameters --> 
        <!-- ========================================================== --> 

        <context-param> 
                <description>Enable debugging for the application</description> 
                <param-name>debug</param-name> 
                <param-value>true</param-value> 
        </context-param> 
        <context-param> 
                <description>The email address of the administrator, used to send error reports.</description> 
                <param-name>webmaster</param-name> 
                <param-value>address@somedomain.com</param-value> 
        </context-param> 


        <!-- ========================================================== --> 
        <!-- JNDI Environment Variables --> 
        <!-- ========================================================== --> 

        <env-entry> 
                <env-entry-name>webmasterName</env-entry-name> 
                <env-entry-value>Ms. W. Master</env-entry-value> 
                <env-entry-type>java.lang.String</env-entry-type> 
        </env-entry> 
        <env-entry> 
                <env-entry-name>cms/defaultUserSettings/recordsPerPage</env-entry-name> 
                <env-entry-value>30</env-entry-value> 
                <env-entry-type>java.lang.Integer</env-entry-type> 
        </env-entry> 
        <env-entry> 
                <env-entry-name>cms/enableXMLExport</env-entry-name> 
                <env-entry-value>false</env-entry-value> 
                <env-entry-type>java.lang.Boolean</env-entry-type> 
        </env-entry> 
        <env-entry> 
                <env-entry-name>cms/enableEmailNotifications</env-entry-name> 
                <env-entry-value>true</env-entry-value> 
                <env-entry-type>java.lang.Boolean</env-entry-type> 
        </env-entry> 


        <!-- ========================================================== --> 
        <!-- Servlets --> 
        <!-- ========================================================== --> 

        <!-- Simple Servlet, provide a name, class, description and map to URL /servlet/SimpleServlet --> 
        <servlet> 
                <servlet-name>Simple</servlet-name> 
                <servlet-class>SimpleServlet</servlet-class> 
                <description>This is a simple Hello World servlet</description> 
        </servlet> 
        <servlet-mapping> 
                <servlet-name>Simple</servlet-name> 
                <url-pattern>/servlet/SimpleServlet</url-pattern> 
        </servlet-mapping> 

        <!-- CMS Servlet, responds to *.cms URL's --> 
        <servlet> 
                <!-- Identification --> 
                <servlet-name>cms</servlet-name> 
                <servlet-class>com.metawerx.servlets.ContentManagementSystem</servlet-class> 
                <description>This servlet handles requests for the CMS (it is a controller in an MVC architecture)</description> 

                <!-- This servlet has two parameters --> 
                <init-param> 
                        <param-name>debug</param-name> 
                        <param-value>true</param-value> 
                </init-param> 
                <init-param> 
                        <param-name>detail</param-name> 
                        <param-value>2</param-value> 
                </init-param> 

                <!-- Load this servlet when the application starts (call the init() method of the servlet) --> 
                <load-on-startup>5</load-on-startup> 
                <!-- <run-at>0:00, 6:00, 12:00, 18:00</run-at> This tag is only valid for Resin --> 
        </servlet> 

        <!-- Map some URLs to the cms servlet (demonstrates *.extension mapping) --> 
        <servlet-mapping> 
                <!-- For any URL ending in .cms, the cms servlet will be called --> 
                <servlet-name>cms</servlet-name> 
                <url-pattern>*.cms</url-pattern> 
        </servlet-mapping> 

        <!-- Rewriter Servlet, responds to /content/* and /admin/RewriterStatistics URL's --> 
        <!-- Define a servlet to respond to /content/* URL's --> 
        <servlet> 
                <servlet-name>rewriter</servlet-name> 
                <servlet-class>com.metawerx.servlets.URLRewriter</servlet-class> 
        </servlet> 

        <!-- Map some URL's to the rewriter servlet (demonstrates /path/* and specific URL mapping) --> 
        <servlet-mapping> 
                <!-- For any URL starting with /content/, the rewriter servlet will be called --> 
                <servlet-name>rewriter</servlet-name> 
                <url-pattern>/content/*</url-pattern> 
        </servlet-mapping> 
        <servlet-mapping> 
                <!-- The rewriter servlet can also be called directly as /admin/RewriterStatistics, to return stats --> 
                <servlet-name>rewriter</servlet-name> 
                <url-pattern>/admin/RewriterStatistics</url-pattern> 
        </servlet-mapping> 

        <!-- PathJSP Servlet, maps /shop/item/* URL's to a JSP file --> 
        <!-- Define a JSP file to respond to /shop/item/* URL's --> 
        <servlet> 
                <servlet-name>pathjsp</servlet-name> 
                <jsp-file>pathfinder.jsp</jsp-file> 
        </servlet> 

        <!-- Map some URL's to the pathjsp servlet (demonstrates /long/path/* URL mapping) --> 
        <servlet-mapping> 
                <!-- For any URL starting with /shop/item/, the pathjsp servlet will be called --> 
                <servlet-name>pathjsp</servlet-name> 
                <url-pattern>/shop/item/*</url-pattern> 
        </servlet-mapping> 


        <!-- ========================================================== --> 
        <!-- Filters --> 
        <!-- ========================================================== --> 

        <!-- Example filter to set character encoding on each request (from Tomcat servlets-examples context) --> 
        <filter> 
                <filter-name>Set Character Encoding</filter-name> 
                <filter-class>filters.SetCharacterEncodingFilter</filter-class> 
                <init-param> 
                        <param-name>encoding</param-name> 
                        <param-value>EUC_JP</param-value> 
                </init-param> 
        </filter> 
        <filter-mapping> 
                <filter-name>Set Character Encoding</filter-name> 
                <url-pattern>/*</url-pattern> 
        </filter-mapping> 

        <!-- Example filter to dump the HTTP request at the top of each page (from Tomcat servlets-examples context) --> 
        <filter> 
                <filter-name>Request Dumper Filter</filter-name> 
                <filter-class>filters.RequestDumperFilter</filter-class> 
        </filter> 
        <filter-mapping> 
                <filter-name>Request Dumper Filter</filter-name> 
                <url-pattern>/*</url-pattern> 
        </filter-mapping> 


        <!-- ========================================================== --> 
        <!-- Listeners --> 
        <!-- ========================================================== --> 

        <!-- Define example application events listeners --> 
        <listener> 
                <listener-class>com.metawerx.listener.ContextListener</listener-class> 
        </listener> 
        <listener> 
                <listener-class>com.metawerx.listener.SessionListener</listener-class> 
        </listener> 


        <!-- ========================================================== --> 
        <!-- Security --> 
        <!-- ========================================================== --> 

        <!-- Define roles --> 
        <security-role> 
                <role-name>admin</role-name> 
        </security-role> 
        <security-role> 
                <role-name>cms_editors</role-name> 
        </security-role> 
         
        <!-- Define a constraint to restrict access to /private/* --> 
        <security-constraint> 

                <display-name>Security constraint for the /private folder</display-name> 

                <web-resource-collection> 
                         
                        <web-resource-name>Protected Area</web-resource-name> 
                        <url-pattern>/private/*</url-pattern> 
                         
                        <!-- If you list http methods, only those methods are protected. --> 
                        <!-- Leave this commented out to protect all access --> 
                        <!-- 
                        <http-method>DELETE</http-method> 
                        <http-method>GET</http-method> 
                        <http-method>POST</http-method> 
                        <http-method>PUT</http-method> 
                        --> 

                </web-resource-collection> 

                <auth-constraint> 
                        <!-- Only only administrator and CMS editors to access this area --> 
                        <role-name>admin</role-name> 
                        <role-name>cms_editors</role-name> 
                </auth-constraint> 

        </security-constraint> 

        <!-- FORM based authentication --> 
        <!-- Leave this commented out, we will use BASIC (HTTP) authentication instead --> 
        <!-- 
        <login-config> 
                <auth-method>FORM</auth-method> 
                <form-login-config> 
                        <form-login-page>/login.jsp</form-login-page> 
                        <form-error-page>/error.jsp</form-error-page> 
                </form-login-config> 
        </login-config> 
        --> 
        <!-- This application uses BASIC authentication --> 
        <login-config> 
                <auth-method>BASIC</auth-method> 
                <realm-name>Editor Login</realm-name> 
        </login-config> 

        <!-- Define a constraint to force SSL on all pages in the application --> 
        <security-constraint> 

                <web-resource-collection> 
                        <web-resource-name>Entire Application</web-resource-name> 
                        <url-pattern>/*</url-pattern> 
                </web-resource-collection> 

                <user-data-constraint> 
                        <transport-guarantee>CONFIDENTIAL</transport-guarantee> 
                </user-data-constraint> 

        </security-constraint> 


        <!-- ========================================================== --> 
        <!-- Error Handler --> 
        <!-- ========================================================== --> 

        <!-- Define an error handler for 404 pages --> 
        <error-page> 
                <error-code>404</error-code> 
                <location>/error404.jsp</location> 
        </error-page> 

        <!-- Define an error handler for java.lang.Throwable --> 
        <error-page> 
                <exception-type>java.lang.Throwable</exception-type> 
                <location>/errorThrowable.jsp</location> 
        </error-page> 


        <!-- ========================================================== --> 
        <!-- Extra MIME types --> 
        <!-- ========================================================== --> 

        <!-- Set XML mime-mapping so spreadsheets open properly instead of being sent as an octet/stream --> 
        <mime-mapping> 
                <extension>xls</extension> 
                <mime-type>application/vnd.ms-excel</mime-type> 
        </mime-mapping> 


        <!-- ========================================================== --> 
        <!-- Locale --> 
        <!-- ========================================================== --> 

        <!-- Set Locale Encoding --> 
        <locale-encoding-mapping-list> 
                <locale-encoding-mapping> 
                        <locale>ja</locale> 
                        <encoding>Shift_JIS</encoding> 
                </locale-encoding-mapping> 
        </locale-encoding-mapping-list> 


        <!-- ========================================================== --> 
        <!-- Welcome Files --> 
        <!-- ========================================================== --> 

        <!-- Define, in order of preference, which file to show when no filename is defined in the path --> 
        <!-- eg: when user goes to http://yoursite.com/ or http://yoursite.com/somefolder --> 
        <!-- Defaults are provided in the server-wide web.xml file, such as index.jsp, index.htm --> 
        <!-- Note: using this tag overrides the defaults, so don't forget to add them here --> 
        <welcome-file-list> 
                <!-- Use index.swf if present, or splash.jsp, otherwise just look for the normal defaults --> 
                <welcome-file>index.swf</welcome-file> 
                <welcome-file>splash.jsp</welcome-file> 
                <welcome-file>index.html</welcome-file> 
                <welcome-file>index.htm</welcome-file> 
                <welcome-file>index.jsp</welcome-file> 
        </welcome-file-list> 

</web-app>