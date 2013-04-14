Location-Redirect Service
=====================
Send your clients to this endpoint and back.
The redirect will have the Country, City, Region, LatLong and User-Agent appended as fragments to the
redirect_uri parameter.

Example:
    GET http://location-redirect.appspot.com/?redirect_uri=http://localhost/RestTest/

responds to the client with a 302 redirect and header
    Location: http://localhost/RestTest/?X-AppEngine-City=ha+noi&X-AppEngine-Country=VN&X-AppEngine-Region=%3F&X-AppEngine-CityLatLong=21.033333%2C105.850000&User-Agent=Mozilla%2F5.0+%28X11%3B+Linux+x86_64%29+AppleWebKit%2F537.4+%28KHTML%2C+like+Gecko%29+Chrome%2F22.0.1229.94+Safari%2F537.4

Easy way for a non-Google-App-Engine service to get a rough location of the client, and an
easy way for a native application to get its location without acquiring a GPS fix, and to get its User-Agent.
