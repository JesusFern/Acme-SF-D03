/*
 * EmployerJobUpdateService.java
 *
 * Copyright (C) 2012-2024 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.auditRecords.Mark;
import acme.entities.codeAudits.CodeAudit;
import acme.features.auditor.codeAudit.AuditorCodeAuditRepository;
import acme.roles.Auditor;

@Service
public class AuditorAuditRecordUpdateService extends AbstractService<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private AuditorCodeAuditRepository repository;


	@Override
	public void authorise() {
		boolean status;
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		AuditRecord object;

		int masterId;
		CodeAudit codeAudit;
		masterId = super.getRequest().getData("masterId", int.class);
		codeAudit = this.repository.findOneCodeAuditById(masterId);

		object = new AuditRecord();
		object.setCode("");
		object.setCodeAudit(codeAudit);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final AuditRecord object) {
		assert object != null;

		super.bind(object, "code", "periodStart", "periodEnd", "mark", "link");

	}

	@Override
	public void validate(final AuditRecord object) {
		assert object != null;
	}

	@Override
	public void perform(final AuditRecord object) {
		assert object != null;
		this.repository.save(object);
	}

	@Override
	public void unbind(final AuditRecord object) {
		assert object != null;
		SelectChoices choices;
		Dataset dataset;

		choices = SelectChoices.from(Mark.class, object.getMark());

		dataset = super.unbind(object, "code", "periodStart", "periodEnd", "mark", "link");
		dataset.put("marks", choices);
		dataset.put("masterId", super.getRequest().getData("masterId", int.class));

		super.getResponse().addData(dataset);
	}

}