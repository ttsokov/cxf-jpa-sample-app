package sample_app.dao;

import sample_app.model.Pump;

public class PumpDao extends AbstractJpaDao<Long, Pump> {

	public PumpDao() {
		super(Pump.class);
	}

	@Override
	public void create(Pump pump) {
		super.create(pump);
	}

	@Override
	public void delete(String number) {
		super.delete(number);
	}

	public Pump findByNumber(String number) {
		// TODO find by number from DB
		Pump pump = new Pump();
		pump.setNumber(123l);

		return pump;
	}

}
