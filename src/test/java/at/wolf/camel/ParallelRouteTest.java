package at.wolf.camel;

import at.wolf.camel.route.ParallelRoute;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

/**
 * Created by Andreas Wolf on 01.04.17.
 */
public class ParallelRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;

    @Test
    public void testSendMessage() throws Exception {

        template.sendBody("direct:parallelCsv", null);

    }

    @Override
    protected RouteBuilder createRouteBuilder() {
        return new ParallelRoute();
    }
}
