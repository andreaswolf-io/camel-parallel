package at.wolf.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.zipfile.ZipSplitter;

/**
 * Defines a Camel Route, which
 * -) download a zip file
 * -) deflates zip file
 * -) splits up text file per line
 * -) executes the split lines in parallel
 *
 * Created by Andreas Wolf on 01.04.17.
 */
public class ParallelRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("direct:parallelCsv").
        to("http://download.geonames.org/export/zip/AT.zip")
            .log("Downloaded zip file")
            .to("log:at.wolf.camel?showOut=true")
            .split(new ZipSplitter())
            .streaming().filter(header(Exchange.FILE_NAME).isEqualTo("AT.txt"))
            .split().tokenize("\n").parallelProcessing()
            .log(body().toString())
        .end()
        ;
    }
}
