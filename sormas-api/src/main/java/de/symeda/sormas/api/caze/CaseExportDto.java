/*******************************************************************************
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2018 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.api.caze;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import de.symeda.sormas.api.Disease;
import de.symeda.sormas.api.DiseaseHelper;
import de.symeda.sormas.api.ExportTarget;
import de.symeda.sormas.api.ExportType;
import de.symeda.sormas.api.clinicalcourse.HealthConditionsDto;
import de.symeda.sormas.api.facility.FacilityHelper;
import de.symeda.sormas.api.i18n.I18nProperties;
import de.symeda.sormas.api.i18n.Strings;
import de.symeda.sormas.api.person.ApproximateAgeType;
import de.symeda.sormas.api.person.ApproximateAgeType.ApproximateAgeHelper;
import de.symeda.sormas.api.person.BurialConductor;
import de.symeda.sormas.api.person.EducationType;
import de.symeda.sormas.api.person.OccupationType;
import de.symeda.sormas.api.person.PersonDto;
import de.symeda.sormas.api.person.PersonHelper;
import de.symeda.sormas.api.person.PresentCondition;
import de.symeda.sormas.api.person.Sex;
import de.symeda.sormas.api.sample.PathogenTestResultType;
import de.symeda.sormas.api.symptoms.SymptomsDto;
import de.symeda.sormas.api.utils.DateHelper;
import de.symeda.sormas.api.utils.Order;
import de.symeda.sormas.api.utils.YesNoUnknown;

public class CaseExportDto implements Serializable {

	private static final long serialVersionUID = 8581579464816945555L;

	public static final String I18N_PREFIX = "CaseExport";

	private String country;
	private long id;
	private long personId;
	private long epiDataId;
	private long symptomsId;
	private long hospitalizationId;
	private long districtId;
	private long healthConditionsId;
	private String uuid;
	private String epidNumber;
	private String diseaseFormatted;
	private Disease disease;
	private String person;
	private Sex sex;
	private String approximateAge;
	private String ageGroup;
	private String birthdate;
	private Date reportDate;
	private String region;
	private String district;
	private String community;
	private String healthFacility;
	private CaseClassification caseClassification;
	private InvestigationStatus investigationStatus;
	private CaseClassification maxSourceCaseClassifcation;
	private CaseOutcome outcome;
	private String associatedWithOutbreak;
	private YesNoUnknown admittedToHealthFacility;
	private Date admissionDate;
	private Date dischargeDate;
	private YesNoUnknown leftAgainstAdvice;
	private String initialDetectionPlace;
	private YesNoUnknown sampleTaken;
	private String sampleDates;
	private String labResults;
	private PresentCondition presentCondition;
	private Date deathDate;
	private String burialInfo;
	private String address;
	private String phone;
	private String occupationType;
	private String educationType;
	private String travelHistory;
	private YesNoUnknown traveled;
	private YesNoUnknown burialAttended;
	private YesNoUnknown directContactConfirmedCase;
	private YesNoUnknown contactWithRodent;
	private SymptomsDto symptoms;
//	private Date onsetDate;
//	private String symptoms;
	private Vaccination vaccination;
	private String vaccinationDoses;
	private Date vaccinationDate;
	private VaccinationInfoSource vaccinationInfoSource;
	private HealthConditionsDto healthConditions;
	private int numberOfPrescriptions;
	private int numberOfTreatments;
	private int numberOfClinicalVisits;

	public CaseExportDto(long id, long personId, long epiDataId, long symptomsId, long hospitalizationId, long districtId, long healthConditionsId,
			String uuid, String epidNumber, Disease disease, String diseaseDetails, String firstName, String lastName, Sex sex,
			Integer approximateAge, ApproximateAgeType approximateAgeType, Integer birthdateDD, Integer birthdateMM, Integer birthdateYYYY, 
			Date reportDate, String region, String district, String community, String healthFacility, String healthFacilityUuid, String healthFacilityDetails, 
			CaseClassification caseClassification, InvestigationStatus investigationStatus, CaseOutcome outcome,
			YesNoUnknown admittedToHealthFacility, Date admissionDate, Date dischargeDate, YesNoUnknown leftAgainstAdvice, 
			PresentCondition presentCondition,  Date deathDate, Date burialDate, BurialConductor burialConductor,String burialPlaceDescription, 
			String phone, String phoneOwner, EducationType educationType, String educationDetails,
			OccupationType occupationType, String occupationDetails, String occupationFacility, String occupationFacilityUuid, String occupationFacilityDetails,
			YesNoUnknown traveled, YesNoUnknown burialAttended, YesNoUnknown directContactConfirmedCase, YesNoUnknown contactWithRodent,
			//Date onsetDate, 
			Vaccination vaccination, String vaccinationDoses, Date vaccinationDate, VaccinationInfoSource vaccinationInfoSource) {
		this.id = id;
		this.personId = personId;
		this.epiDataId = epiDataId;
		this.symptomsId = symptomsId;
		this.hospitalizationId = hospitalizationId;
		this.districtId = districtId;
		this.healthConditionsId = healthConditionsId;
		this.uuid = uuid;
		this.epidNumber = epidNumber;
		this.diseaseFormatted = DiseaseHelper.toString(disease, diseaseDetails);
		this.disease = disease;
		this.person = PersonDto.buildCaption(firstName, lastName);
		this.sex = sex;
		this.approximateAge = ApproximateAgeHelper.formatApproximateAge(approximateAge, approximateAgeType);
		this.ageGroup = ApproximateAgeHelper.getAgeGroupFromAge(approximateAge, approximateAgeType);
		this.birthdate = PersonHelper.formatBirthdate(birthdateDD, birthdateMM, birthdateYYYY);
		this.reportDate = reportDate;
		this.region = region;
		this.district = district;
		this.community = community;
		this.caseClassification = caseClassification;
		this.investigationStatus = investigationStatus;
		this.outcome = outcome;
		this.healthFacility = FacilityHelper.buildFacilityString(healthFacilityUuid, healthFacility, healthFacilityDetails);
		this.admittedToHealthFacility = admittedToHealthFacility;
		this.admissionDate = admissionDate;
		this.dischargeDate = dischargeDate;
		this.leftAgainstAdvice = leftAgainstAdvice;
		this.presentCondition = presentCondition;
		this.deathDate = deathDate;
		this.burialInfo = PersonHelper.buildBurialInfoString(burialDate, burialConductor, burialPlaceDescription);
		this.phone = PersonHelper.buildPhoneString(phone, phoneOwner);
		this.educationType = PersonHelper.buildEducationString(educationType, educationDetails);
		this.occupationType = PersonHelper.buildOccupationString(occupationType, occupationDetails,
				FacilityHelper.buildFacilityString(occupationFacilityUuid, occupationFacility, occupationFacilityDetails));
		this.traveled = traveled;
		this.burialAttended = burialAttended;
		this.directContactConfirmedCase = directContactConfirmedCase;
		this.contactWithRodent = contactWithRodent;
//		this.onsetDate = onsetDate;
		this.vaccination = vaccination;
		this.vaccinationDoses = vaccinationDoses;
		this.vaccinationDate = vaccinationDate;
		this.vaccinationInfoSource = vaccinationInfoSource;
	}

	public CaseReferenceDto toReference() {
		return new CaseReferenceDto(uuid, person);
	}
	
	@Order(0)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getCountry() {
		return country;
	}

	@Order(1)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public long getId() {
		return id;
	}

	public long getPersonId() {
		return personId;
	}

	public long getEpiDataId() {
		return epiDataId;
	}

	public long getSymptomsId() {
		return symptomsId;
	}
	
	public long getHospitalizationId() {
		return hospitalizationId;
	}
	
	public long getDistrictId() {
		return districtId;
	}
	
	public long getHealthConditionsId() {
		return healthConditionsId;
	}

	@Order(2)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getUuid() {
		return uuid;
	}

	@Order(3)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getEpidNumber() {
		return epidNumber;
	}

	@Order(4)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getDiseaseFormatted() {
		return diseaseFormatted;
	}

	@Order(10)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getPerson() {
		return person;
	}

	@Order(11)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Sex getSex() {
		return sex;
	}

	@Order(12)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getApproximateAge() {
		return approximateAge;
	}
	
	@Order(13)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getAgeGroup() {
		return ageGroup;
	}

	@Order(14)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	@Order(20)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Date getReportDate() {
		return reportDate;
	}

	@Order(21)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getRegion() {
		return region;
	}

	@Order(22)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getDistrict() {
		return district;
	}

	@Order(23)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getCommunity() {
		return community;
	}

	@Order(24)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getHealthFacility() {
		return healthFacility;
	}
	
	@Order(25)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getInitialDetectionPlace() {
		return initialDetectionPlace;
	}

	@Order(30)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public CaseClassification getCaseClassification() {
		return caseClassification;
	}

	@Order(31)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public InvestigationStatus getInvestigationStatus() {
		return investigationStatus;
	}

	@Order(32)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public CaseOutcome getOutcome() {
		return outcome;
	}

	@Order(33)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public CaseClassification getMaxSourceCaseClassifcation() {
		return maxSourceCaseClassifcation;
	}
	
	@Order(34)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public String getAssociatedWithOutbreak() {
		return associatedWithOutbreak;
	}

	public void setMaxSourceCaseClassifcation(CaseClassification maxSourceCaseClassifcation) {
		this.maxSourceCaseClassifcation = maxSourceCaseClassifcation;
	}
	
	@Order(40)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public YesNoUnknown getAdmittedToHealthFacility() {
		return admittedToHealthFacility;
	}

	@Order(41)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Date getAdmissionDate() {
		return admissionDate;
	}

	@Order(42)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	@Order(43)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public YesNoUnknown getLeftAgainstAdvice() {
		return leftAgainstAdvice;
	}

	public void setLeftAgainstAdvice(YesNoUnknown leftAgainstAdvice) {
		this.leftAgainstAdvice = leftAgainstAdvice;
	}

	@Order(50)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public PresentCondition getPresentCondition() {
		return presentCondition;
	}

	@Order(51)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public Date getDeathDate() {
		return deathDate;
	}

	@Order(52)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public String getBurialInfo() {
		return burialInfo;
	}

	public void setBurialInfo(String burialInfo) {
		this.burialInfo = burialInfo;
	}

	@Order(60)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getAddress() {
		return address;
	}

	@Order(61)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getPhone() {
		return phone;
	}

	@Order(62)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}

	@Order(63)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getOccupationType() {
		return occupationType;
	}

	public YesNoUnknown getTraveled() {
		return traveled;
	}

	public void setTraveled(YesNoUnknown traveled) {
		this.traveled = traveled;
	}

	@Order(70)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public String getTravelHistory() {
		return travelHistory;
	}

	@Order(71)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public YesNoUnknown getBurialAttended() {
		return burialAttended;
	}

	public void setBurialAttended(YesNoUnknown burialAttended) {
		this.burialAttended = burialAttended;
	}

	@Order(72)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public YesNoUnknown getDirectContactConfirmedCase() {
		return directContactConfirmedCase;
	}

	public void setDirectContactConfirmedCase(YesNoUnknown directContactConfirmedCase) {
		this.directContactConfirmedCase = directContactConfirmedCase;
	}

	@Order(73)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public YesNoUnknown getContactWithRodent() {
		return contactWithRodent;
	}
	
	@Order(80)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Vaccination getVaccination() {
		return vaccination;
	}
	
	@Order(81)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public String getVaccinationDoses() {
		return vaccinationDoses;
	}
	
	@Order(82)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public Date getVaccinationDate() {
		return vaccinationDate;
	}
	
	@Order(83)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public VaccinationInfoSource getVaccinationInfoSource() {
		return vaccinationInfoSource;
	}

	@Order(100)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public YesNoUnknown getSampleTaken() {
		return sampleTaken;
	}

	@Order(101)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public String getSampleDates() {
		return sampleDates;
	}

	@Order(102)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE})
	public String getLabResults() {
		return labResults;
	}


//	@Order(90)
//	public Date getOnsetDate() {
//		return onsetDate;
//	}
//
//	@Order(91)
//	public String getSymptoms() {
//		return symptoms;
//	}	

	@Order(110)
	@ExportTarget(exportTypes = {ExportType.CASE_SURVEILLANCE, ExportType.CASE_MANAGEMENT})
	public SymptomsDto getSymptoms() {
		return symptoms;
	}
	
	@Order(111)
	@ExportTarget(exportTypes = {ExportType.CASE_MANAGEMENT})
	public HealthConditionsDto getHealthConditions() {
		return healthConditions;
	}
	
	@Order(112)
	@ExportTarget(exportTypes = {ExportType.CASE_MANAGEMENT})
	public int getNumberOfPrescriptions() {
		return numberOfPrescriptions;
	}
	
	@Order(113)
	@ExportTarget(exportTypes = {ExportType.CASE_MANAGEMENT})
	public int getNumberOfTreatments() {
		return numberOfTreatments;
	}
	
	@Order(114)
	@ExportTarget(exportTypes = {ExportType.CASE_MANAGEMENT})
	public int getNumberOfClinicalVisits() {
		return numberOfClinicalVisits;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	public void setEpiDataId(long epiDataId) {
		this.epiDataId = epiDataId;
	}

	public void setSymptomsId(long symptomsId) {
		this.symptomsId = symptomsId;
	}
	
	public void setHospitalizationId(long hospitalizationId) {
		this.hospitalizationId = hospitalizationId;
	}
	
	public void setDistrictId(long districtId) {
		this.districtId = districtId;
	}
	
	public void setHealthConditionsId(long healthConditionsId) {
		this.healthConditionsId = healthConditionsId;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setEpidNumber(String epidNumber) {
		this.epidNumber = epidNumber;
	}

	public void setDiseaseFormatted(String diseaseFormatted) {
		this.diseaseFormatted = diseaseFormatted;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setApproximateAge(String age) {
		this.approximateAge = age;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public void setHealthFacility(String healthFacility) {
		this.healthFacility = healthFacility;
	}
	
	public void setAdmittedToHealthFacility(YesNoUnknown admittedToHealthFacility) {
		this.admittedToHealthFacility = admittedToHealthFacility;
	}

	public void setSampleTaken(YesNoUnknown sampleTaken) {
		this.sampleTaken = sampleTaken;
	}

	public void setSampleDates(String sampleDates) {
		this.sampleDates = sampleDates;
	}

	public void setSampleDates(List<Date> sampleDates) {
		StringBuilder sampleDateBuilder = new StringBuilder();
		for (int i = 0; i < sampleDates.size(); i++) {
			if (i > 0) {
				sampleDateBuilder.append(", ");
			}
			sampleDateBuilder.append(DateHelper.formatLocalShortDate(sampleDates.get(i)));
		}
		this.sampleDates = sampleDateBuilder.toString();
	}

	public void setLabResults(String labResults) {
		this.labResults = labResults;
	}

	public void setLabResults(List<PathogenTestResultType> labResults) {
		StringBuilder testResultsBuilder = new StringBuilder();
		for (int i = 0; i < labResults.size(); i++) {
			if (i > 0) {
				testResultsBuilder.append(", ");
			}
			testResultsBuilder.append(labResults.get(i).toString());
		}
		this.labResults = testResultsBuilder.toString();
	}

	public void setCaseClassification(CaseClassification caseClassification) {
		this.caseClassification = caseClassification;
	}

	public void setInvestigationStatus(InvestigationStatus investigationStatus) {
		this.investigationStatus = investigationStatus;
	}

	public void setPresentCondition(PresentCondition presentCondition) {
		this.presentCondition = presentCondition;
	}

	public void setOutcome(CaseOutcome outcome) {
		this.outcome = outcome;
	}
	
	public void setAssociatedWithOutbreak(boolean associatedWithOutbreak) {
		this.associatedWithOutbreak = associatedWithOutbreak ? I18nProperties.getString(Strings.yes) : I18nProperties.getString(Strings.no);
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public void setTravelHistory(String travelHistory) {
		this.travelHistory = travelHistory;
	}

	public void setContactWithRodent(YesNoUnknown contactWithRodent) {
		this.contactWithRodent = contactWithRodent;
	}

	public void setInitialDetectionPlace(String initialDetectionPlace) {
		this.initialDetectionPlace = initialDetectionPlace;
	}

	public void setVaccination(Vaccination vaccination) {
		this.vaccination = vaccination;
	}

	public void setVaccinationDoses(String vaccinationDoses) {
		this.vaccinationDoses = vaccinationDoses;
	}

	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}

	public void setVaccinationInfoSource(VaccinationInfoSource vaccinationInfoSource) {
		this.vaccinationInfoSource = vaccinationInfoSource;
	}

//	public void setOnsetDate(Date onsetDate) {
//		this.onsetDate = onsetDate;
//	}
//
//	public void setSymptoms(String symptoms) {
//		this.symptoms = symptoms;
//	}

	public void setSymptoms(SymptomsDto symptoms) {
		this.symptoms = symptoms;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public void setHealthConditions(HealthConditionsDto healthConditions) {
		this.healthConditions = healthConditions;
	}

	public void setNumberOfPrescriptions(int numberOfPrescriptions) {
		this.numberOfPrescriptions = numberOfPrescriptions;
	}

	public void setNumberOfTreatments(int numberOfTreatments) {
		this.numberOfTreatments = numberOfTreatments;
	}

	public void setNumberOfClinicalVisits(int numberOfClinicalVisits) {
		this.numberOfClinicalVisits = numberOfClinicalVisits;
	}
	
}
