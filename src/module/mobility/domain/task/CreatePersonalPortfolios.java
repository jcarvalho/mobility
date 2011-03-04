package module.mobility.domain.task;

import module.mobility.domain.PersonalPortfolio;
import module.organization.domain.Accountability;
import module.organization.domain.AccountabilityType;
import module.organization.domain.Party;
import module.organization.domain.Person;
import module.organizationIst.domain.IstAccountabilityType;

public class CreatePersonalPortfolios extends CreatePersonalPortfolios_Base {

    public CreatePersonalPortfolios() {
	super();
    }

    @Override
    public void executeTask() {
	AccountabilityType employeesAccountabilityType = IstAccountabilityType.PERSONNEL.readAccountabilityType();
	for (Accountability accountability : employeesAccountabilityType.getAccountabilitiesSet()) {
	    if (accountability.isActiveNow()) {
		final Party party = accountability.getChild();
		if (party.isPerson()) {
		    Person person = (Person) party;
		    if (!person.hasPersonalPortfolio()) {
			PersonalPortfolio.create(person);
		    }
		}
	    }
	}
    }

    @Override
    public String getLocalizedName() {
	return getClass().getName();
    }
}