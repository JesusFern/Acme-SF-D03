
package acme.features.sponsor.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.data.datatypes.Money;
import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Invoice i where i.tax <= 21.00 and i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	Integer totalNumberOfInvoicesWithTaxLessOrEquals21(int id);

	@Query("select count(s) from Sponsorship s where s.link is not null and s.sponsor.id = :id and s.draftMode = false")
	Integer totalNumberOfSponsorshipsLink(int id);

	@Query("select avg(s.amount.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false")
	Double averageSponsorshipsAmount(int id);

	@Query("select stddev(s.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false")
	Money deviationSponsorshipsAmount(int id);

	@Query("select min(s.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false")
	Money minimumSponsorshipsAmount(int id);

	@Query("select max(s.amount) from Sponsorship s where s.sponsor.id = :id and s.draftMode = false")
	Money maximumSponsorshipsAmount(int id);

	@Query("select avg(i.quantity.amount) from Invoice i where i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	Double averageInvoicesQuantity(int id);

	@Query("select stddev(i.quantity) from Invoice i where i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	Money deviationInvoicesQuantity(int id);

	@Query("select min(i.quantity) from Invoice i where i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	Money minimumInvoicesQuantity(int id);

	@Query("select max(i.quantity) from Invoice i where i.sponsorship.sponsor.id = :id and i.sponsorship.draftMode = false")
	Money maximumInvoicesQuantity(int id);
}
