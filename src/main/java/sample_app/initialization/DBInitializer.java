package sample_app.initialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.gson.Gson;

import sample_app.dao.AbstractJpaDao;
import sample_app.dao.PumpDao;
import sample_app.model.Pump;

public class DBInitializer {
	// private static final Logger logger =
	// LoggerFactory.getLogger(DBInitializer.class);

	private AbstractJpaDao<Long, Pump> pumpDao = new PumpDao();

	private static final String PUMPS_CONFIG = "/data/pumps.json";
	private static final String ERROR_LOADING_INITIAL_CONFIGURATION_MESSAGE = "Error while initializing sample app initial configuration [{}]";

	public void initData() {
		new DataImport<Long, Pump>()
				.importFromJSON(Arrays.asList(parseJsonFileConfiguration(PUMPS_CONFIG, Pump[].class)), pumpDao);

	}

	private <T> T parseJsonFileConfiguration(String filename, Class<T> type) {
		T result = null;
		InputStream resourceAsStream = DBInitializer.class.getResourceAsStream(filename);
		InputStreamReader reader = new InputStreamReader(resourceAsStream);
		try {
			if (resourceAsStream.available() != 0) {
				result = new Gson().fromJson(reader, type);
			}
		} catch (IOException e) {
			// logger.error(ERROR_LOADING_INITIAL_CONFIGURATION_MESSAGE, e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
