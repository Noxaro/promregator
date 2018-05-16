package org.cloudfoundry.promregator.fetcher;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.client.methods.HttpGet;
import org.cloudfoundry.promregator.auth.AuthenticationEnricher;
import org.cloudfoundry.promregator.rewrite.AbstractMetricFamilySamplesEnricher;

import io.prometheus.client.Gauge;
import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.Histogram.Timer;

public class MetricsFetcherSimulator implements MetricsFetcher {

	private String accessURL;
	private AuthenticationEnricher ae;
	private AbstractMetricFamilySamplesEnricher mfse;
	private MetricsFetcherMetrics mfm;
	private Gauge.Child up;
	
	private Random randomLatency = new Random();
	
	private static String SIM_TEXT004;
	
	static {
		InputStream is = MetricsFetcherSimulator.class.getResourceAsStream("simulation.text004");
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] buffer = new byte[256*1024];
		try {
			int len = bis.read(buffer);
			SIM_TEXT004 = new String(buffer, 0, len, "UTF-8");
		} catch (IOException e) {
			SIM_TEXT004 = "";
		} finally {
			try {
				bis.close();
			} catch (IOException e) {
				SIM_TEXT004 = "";
			}
		}
	}
	
	public MetricsFetcherSimulator(String accessURL, AuthenticationEnricher ae,
			AbstractMetricFamilySamplesEnricher mfse, MetricsFetcherMetrics mfm, Gauge.Child up) {
				this.accessURL = accessURL;
				this.ae = ae;
				this.mfse = mfse;
				this.mfm = mfm;
				this.up = up;
		
	}

	@Override
	public HashMap<String, MetricFamilySamples> call() throws Exception {
		Timer timer = null;
		if (this.mfm.getLatencyRequest() != null) {
			timer = this.mfm.getLatencyRequest().startTimer();
		}
		
		HttpGet httpget = new HttpGet(this.accessURL);
		this.ae.enrichWithAuthentication(httpget);
		
		String result = SIM_TEXT004;
		
		TextFormat004Parser parser = new TextFormat004Parser(result);
		HashMap<String, MetricFamilySamples> emfs = parser.parse();
		
		emfs = this.mfse.determineEnumerationOfMetricFamilySamples(emfs);
		
		int latency = this.randomLatency.nextInt(300);
		
		Thread.sleep(latency);
		
		this.up.set(1);
		
		if (timer != null) {
			timer.observeDuration();
		}
		
		return emfs;
	}

}
