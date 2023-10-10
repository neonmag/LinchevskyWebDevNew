package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.CharsetFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets()
    {
        // Третій спосіб конфігурування фільтрів сервлетів - ІоС
        filter("/*").through(CharsetFilter.class);
        serve("/").with(HomeServlet.class);
        serve("/filters").with(FiltersServlet.class);
        serve("/ioc").with(IoCServlet.class);
        serve("/signup").with(SignupServlet.class);
    }
}
