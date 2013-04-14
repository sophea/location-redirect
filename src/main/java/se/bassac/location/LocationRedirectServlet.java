/*
 * INSERT COPYRIGHT HERE
 */

package se.bassac.location;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author sosandstrom
 */
public class LocationRedirectServlet extends HttpServlet {
    
    public static final String NAME_COUNTRY = "X-AppEngine-Country";
    public static final String NAME_CITY = "X-AppEngine-City";
    public static final String NAME_REGION = "X-AppEngine-Region";
    public static final String NAME_LATLONG = "X-AppEngine-CityLatLong";
    public static final String NAME_USER_AGENT = "User-Agent";
    public static final String NAME_REDIRECT_URI = "redirect_uri";
    protected static final String HEADERS[] = {NAME_CITY, NAME_COUNTRY, 
        NAME_REGION, NAME_LATLONG,
        NAME_USER_AGENT};
    
    static final Logger LOG = LoggerFactory.getLogger(LocationRedirectServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // correct request?
        final String redirectUri = request.getParameter(NAME_REDIRECT_URI);
        LOG.debug("redirect_uri={}", redirectUri);
        if (null != redirectUri) {
            StringBuffer url = new StringBuffer(redirectUri);
            
            // copy request headers to response
            String value;
            for (String name : HEADERS) {
                value = request.getHeader(name);
                LOG.debug("{}: {}", name, value);
                if (null != value) {
                
                    // as header
                    response.setHeader(name, value);

                    // as param or fragment
                    if (-1 < url.indexOf("?") || -1 < url.indexOf("#")) {
                        url.append('&');
                    }
                    else {
                        url.append("?");
                    }
                    url.append(name);
                    url.append('=');
                    url.append(URLEncoder.encode(value, "UTF-8"));
                }
            }
            value = url.toString();
            LOG.info(value);
            response.sendRedirect(value);
            return;
        }
        response.sendError(400);
    }

}
