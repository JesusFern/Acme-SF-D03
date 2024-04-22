
package acme.forms;

import acme.client.data.AbstractForm;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorDashboard extends AbstractForm {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalNumberOfInvoicesWithTaxLessOrEquals21;
	Integer						totalNumberOfSponsorshipsLink;

	Double						averageSponsorshipsAmount;
	Money						deviationSponsorshipsAmount;
	Money						minimumSponsorshipsAmount;
	Money						maximumSponsorshipsAmount;

	Double						averageInvoicesQuantity;
	Money						deviationInvoicesQuantity;
	Money						minimumInvoicesQuantity;
	Money						maximumInvoicesQuantity;

}
