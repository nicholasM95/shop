package be.nicholas.shop.core.interceptor;

import com.microsoft.applicationinsights.extensibility.TelemetryProcessor;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.telemetry.Telemetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Component
public class TelemetryInterceptor implements TelemetryProcessor {

    @Value("${application.version}")
    private String appVersion;

    @Override
    public boolean process(Telemetry telemetry) {
        telemetry.getContext().getComponent().setVersion(appVersion);

        if (telemetry instanceof RequestTelemetry) {
            String path = getPath((RequestTelemetry) telemetry);
            return !path.startsWith("/actuator/health");
        }
        return true;
    }

    private String getPath(RequestTelemetry requestTelemetry) {
        try {
            return requestTelemetry.getUrl().getPath();
        } catch (MalformedURLException e) {
            return "";
        }
    }
}
