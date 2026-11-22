export const meta = {
  name: 'obstetric-trauma-protocols',
  description: 'Write obstetric-emergency and trauma protocols (groups chosen by args), adversarially verify every number and safety point against sources, apply confirmed fixes',
  phases: [
    { title: 'Write', detail: 'one writer per group, India-first primary sources' },
    { title: 'Verify', detail: 'two independent lenses: numbers, safety/attribution' },
    { title: 'Fix', detail: 'fixer re-confirms each issue against the source before editing' },
  ],
}

const REPO = '/home/user/Medicalaid'
const APP = REPO + '/composeApp/src/commonMain/kotlin/com/medicalaid/app'
const ROOT = APP + '/content'
const DATA = ROOT + '/protocols/data'
const SCRATCH = '/tmp/claude-0/-home-user-Medicalaid/5ca48dd6-74ab-5ae0-9cf9-16fd9a4485a1/scratchpad/obtrauma'

const SCORE_IDS = 'qsofa gcs nihss abcd2 rosier cha2ds2-vasc has-bled wells-dvt wells-pe perc heart timi-ua-nstemi curb-65 news2 alvarado air glasgow-blatchford child-pugh centor-mcisaac bishop ottawa-ankle ottawa-foot canadian-ct-head'

const COMMON = `You are working on a clinician-facing Kotlin Multiplatform app at ${REPO} used by emergency, casualty and obstetric doctors in India (Punjab), from district hospitals to medical colleges. Accuracy matters more than speed. The app summarises guidelines with a citation on every step; it reports what sources say (attributed where sources differ, e.g. "MoHFW: …", "WHO 2023: …", "NICE NG232: …", "RCOG GTG 52: …"), it does not instruct. It is decision support for qualified clinicians.

Read first:
- ${ROOT}/protocols/ProtocolModel.kt (Protocol(id,title,summary,keywords,category,steps,notes,citations) — category is REQUIRED; Step(title,timing,points,checklistTitle,checklist,tool,scoreIds,doses,citations); StepDose(label, dose: WeightDose, note, maxTotal); ProtocolTool {OnsetClock, ThrombolysisDose, BurnsChart, AcidBase}; ProtocolNote; ProtocolCategory {Cardiac, Respiratory, Neuro, Metabolic, Infection, Obstetric, Trauma, Environmental, Poisoning})
- ${DATA}/Stroke.kt (complete worked example — match its structure, attribution style, Source entries and citation locators)
- ${ROOT}/Model.kt (Source(id, authority, document, title, year, url, status); Authority enum incl. MoHFW, ICMR, NDMA, FOGSI, ISCCM, CSI (India), WHO, ACOG, RCOG, FIGO, SOGC, SMFM, NICE, AHA, ERC, RCUK, BOA, BTF, ACSCOT, WSES, EAST, CDC, FDA, Paper; WeightDose(minPerKg, maxPerKg, unit, per, ceilingPerKg); cite(sourceId, locator))
- ${ROOT}/Sources.kt and the other files in ${DATA}/ and ${ROOT}/*.kt — existing Source ids may be cited; do not duplicate a document that already has a Source, cite its id instead. Other writers are filling sibling files in parallel; your Source ids must use your group prefix.

SOURCES — India first, then international: MoHFW / NHM maternal health and trauma documents (e.g. MoHFW guidelines on PPH, MgSO4, SBA/LaQshya/MNH toolkit; National Rabies Control Programme / NCDC guidelines; Universal Immunization Programme Td guidance), ICMR Standard Treatment Workflows (https://www.icmr.gov.in/standard-treatment-workflows-stws), FOGSI guidance, AIIMS JPNATC; then current international guidelines (WHO, NICE, RCOG, ACOG, FIGO, ERC/AHA 2025, European trauma bleeding guideline, Brain Trauma Foundation, BOA BOAST, WSES, EAST). Check for 2024–2026 updates and use the latest edition. The ATLS manual is proprietary — do not copy it or cite it as the source of a number; use openly published guidelines instead. Verify EVERY dose, threshold, time window and cut-off in the primary document with WebSearch/WebFetch (PMC/PubMed full texts, society and government websites, DailyMed/openFDA for product facts). Never fill from memory; if you cannot verify something, leave it out and list it in your report. Official https URLs (DOI acceptable). Save downloads only under ${SCRATCH}/.

WRITING RULES: 5–9 steps per protocol, 2–6 points per step (each ≤ ~25 words), attributed where sources differ; \`timing\` on time-critical steps; \`checklist\` for criteria, contraindications, red flags or don'ts; \`doses\` (StepDose) for every weight-based dose with maxTotal when the source caps it, fixed doses in points (with route, rate and repeat); \`scoreIds\` to link existing scores where relevant (available ids: ${SCORE_IDS}); every Step has citations with locators (section/table/recommendation number). Keywords: abbreviations, Hindi/Punjabi lay terms and brand names Indian clinicians use. ProtocolNotes for India vs international differences and real evidence controversies. Kotlin must be valid: escape any dollar sign as \\$, escape double quotes inside strings; imports as needed (Authority, Source, WeightDose, cite, Protocol, ProtocolCategory, ProtocolTool, Step, StepDose, ProtocolNote). Edit ONLY your assigned file(s). Do NOT run gradle.`

const GROUPS = [
  {
    key: 'eclampsia', files: [`${DATA}/Eclampsia.kt`], sourcesVal: 'eclampsiaSources', protocolsVal: 'eclampsiaProtocols', category: 'Obstetric', prefix: 'ob-ecl-',
    extraVerify: `Also compare with the existing pregnancy page "hypertension" in ${ROOT}/PregnancyConditions.kt (magnesium regimens, BP thresholds, antihypertensive doses) and report any contradiction between the two (say which one the source supports).`,
    brief: `Read the existing pregnancy page id "hypertension" in ${ROOT}/PregnancyConditions.kt first — it already lists ACOG IV/IM and MoHFW Pritchard magnesium regimens and severe-range BP order sets. Reuse its Source ids where the same document applies and stay consistent; if you find an error in that page, report it (do not edit it).
1. id "eclampsia" — Eclampsia (keywords eclampsia, fits in pregnancy, convulsion in pregnancy, postpartum fits, MgSO4, magnesium sulphate, Pritchard, Zuspan, toxaemia, PIH): immediate care during a fit (help, left lateral, airway, oxygen, prevent injury; what sources say about diazepam when magnesium is available); magnesium sulfate — MoHFW Pritchard (IM) regimen and IV (Zuspan/ACOG/NICE/WHO) regimen, loading and maintenance, duration after delivery/last fit, dose for a recurrent fit; monitoring checklist (respiratory rate, patellar reflex, urine output thresholds) and when to withhold; toxicity and calcium gluconate antidote dose/rate; reduced dose or monitoring in renal impairment as sourced; loading dose before referral from a peripheral facility (MoHFW); BP control (link to severe pre-eclampsia step); delivery after stabilisation; postpartum eclampsia window.
2. id "severe-pre-eclampsia" — severe features and HELLP criteria (ACOG PB 222, NICE NG133, MoHFW); severe-range BP threshold and treatment time target; IV labetalol escalation, oral immediate-release nifedipine and IV hydralazine regimens with maxima as sourced; BP targets; magnesium prophylaxis indications; fluid restriction / pulmonary oedema risk; timing and mode of delivery; corticosteroids for fetal lung maturity window; postpartum monitoring.`,
  },
  {
    key: 'pph', files: [`${DATA}/PostpartumHaemorrhage.kt`], sourcesVal: 'pphSources', protocolsVal: 'pphProtocols', category: 'Obstetric', prefix: 'ob-pph-',
    brief: `1. id "pph" — Postpartum haemorrhage (keywords PPH, postpartum bleeding, atonic PPH, oxytocin, Pitocin, methergine, carboprost, Prostodin, misoprostol, carbetocin, TXA, tranexamic, B-Lynch, NASG, balloon tamponade, condom catheter): definitions and objective measurement (WHO 2023 consolidated PPH recommendations, E-MOTIVE trial/WHO 2023 bundle and its trigger criteria); first-response bundle and timing; uterotonics — oxytocin (bolus/infusion), ergometrine/methylergometrine (contraindications: hypertension, pre-eclampsia, cardiac disease), carboprost (dose, interval, maximum; asthma), misoprostol (dose, route), carbetocin (prevention/treatment status as sourced); tranexamic acid dose, timing window from birth and repeat (WOMAN trial / WHO 2017); 4 Ts checklist (tone, trauma, tissue, thrombin); shock index use as sourced; fluids, blood and massive transfusion; refractory PPH — bimanual and aortic compression, uterine balloon tamponade (WHO 2023 stance), NASG during transfer (MoHFW), surgical options and early senior/anaesthetic escalation; prevention (AMTSL — prophylactic uterotonic choice and dose); secondary PPH. India: MoHFW PPH guidance (latest), SBA/MNH toolkit, FOGSI; international: WHO 2023, FIGO 2022, RCOG GTG 52, ACOG PB 183.`,
  },
  {
    key: 'maternal', files: [`${DATA}/MaternalCollapse.kt`], sourcesVal: 'maternalCollapseSources', protocolsVal: 'maternalCollapseProtocols', category: 'Obstetric', prefix: 'ob-mat-',
    brief: `1. id "maternal-collapse" — Maternal collapse and cardiac arrest in pregnancy (keywords maternal arrest, perimortem caesarean, resuscitative hysterotomy, amniotic fluid embolism, AFE): causes checklist (4 Hs/4 Ts plus obstetric causes); manual left uterine displacement; CPR and defibrillation modifications as sourced (AHA 2025 maternal cardiac arrest, ERC 2025 special circumstances, RCOG GTG 56); IV access above the diaphragm; early airway; resuscitative hysterotomy — gestation criterion (fundal height) and timing (decision/delivery minutes as each source states); specific reversible causes — magnesium toxicity (calcium), local anaesthetic systemic toxicity (lipid emulsion dose as sourced), haemorrhage (link PPH protocol by name), eclampsia; amniotic fluid embolism recognition and management (SMFM 2016 / RCOG) — include only regimens a guideline states; post-arrest care.
2. id "trauma-in-pregnancy" — primary survey modifications and physiology; uterine displacement/left lateral positioning; oxygen and fluids; imaging is not withheld (ACOG CO 723 radiation guidance); fetal assessment after maternal stabilisation — continuous fetal monitoring duration criteria (as sourced); placental abruption and uterine rupture signs; Kleihauer–Betke and anti-D for Rh-negative women; tetanus; indications for perimortem caesarean (link maternal-collapse). Sources: SOGC trauma in pregnancy guideline, ACOG, EAST, RCOG, Indian sources if available.`,
  },
  {
    key: 'trauma', files: [`${DATA}/Trauma.kt`], sourcesVal: 'traumaSources', protocolsVal: 'traumaProtocols', category: 'Trauma', prefix: 'tr-',
    brief: `1. id "major-trauma" — initial assessment of the injured patient (keywords polytrauma, RTA, road traffic accident, accident, primary survey, ABCDE, eFAST, trauma call): catastrophic haemorrhage control (direct pressure, tourniquets, haemostatic dressings as sourced); airway with cervical spine protection (link the spinal injury protocol by name); breathing — tension pneumothorax recognition and decompression (technique/site as sourced), open pneumothorax, massive haemothorax, flail chest; circulation (link traumatic haemorrhage protocol); disability (link scoreIds gcs; pupils; glucose); exposure and hypothermia prevention; imaging (eFAST, whole-body CT indications — NICE NG39); analgesia (as sourced, e.g. IV morphine, ketamine); secondary survey; transfer to a trauma centre; WHO Basic Emergency Care and Indian sources (MoHFW/AIIMS JPNATC, ICMR STW) where available.
2. id "traumatic-haemorrhage" — recognition of haemorrhagic shock (shock index etc. as sourced); permissive hypotension targets and exceptions (TBI); tranexamic acid — dose, infusion, time window and do-not-start-after limit (CRASH-2, European guideline, NICE); damage-control resuscitation; blood component ratios and massive transfusion triggers; calcium; avoid crystalloid excess, hypothermia, acidosis; pelvic binder; reversal of anticoagulants/antiplatelets (PCC, vitamin K, idarucizumab, andexanet as sourced); European guideline on management of major bleeding and coagulopathy following trauma (latest edition), NICE NG39, WHO.
3. id "chest-trauma" — tension pneumothorax, haemothorax (drain and thresholds for thoracotomy as sourced), cardiac tamponade, flail chest and rib fractures (analgesia, regional blocks; EAST guidance), blunt cardiac injury screening (ECG/troponin, EAST), penetrating chest injury.
4. id "traumatic-cardiac-arrest" — ERC 2025 traumatic cardiac arrest algorithm (simultaneous treatment of reversible causes: hypovolaemia, hypoxia, tension pneumothorax, tamponade; bilateral thoracostomy), AHA 2025 position, resuscitative thoracotomy indications (EAST/WTA as sourced), when resuscitation is futile as sourced.`,
  },
  {
    key: 'headspine',
    files: [`${DATA}/TraumaHeadSpine.kt`, `${ROOT}/scores/data/TraumaScores.kt`, `${REPO}/composeApp/src/commonTest/kotlin/com/medicalaid/app/content/scores/TraumaScoresTest.kt`],
    sourcesVal: 'headSpineSources', protocolsVal: 'headSpineProtocols', category: 'Trauma', prefix: 'tr-hs-',
    brief: `A) SCORES — overwrite ${ROOT}/scores/data/TraumaScores.kt (placeholder) defining \`internal val traumaScoreSources: List<Source>\` and \`internal val traumaScores: List<Score>\` with two decision rules, category ScoreCategory.Emergency: id "canadian-c-spine" (Canadian C-spine Rule, Stiell et al. JAMA 2001 — high-risk factors, low-risk factors allowing assessment, active rotation 45°; applicability/exclusion criteria) and id "nexus-c-spine" (NEXUS low-risk criteria, Hoffman et al. NEJM 2000). Read ${ROOT}/scores/ScoreModel.kt, ${ROOT}/scores/ScoreEngine.kt and the "canadian-ct-head" score in ${ROOT}/scores/data/AcuteCare.kt as the worked example (sections, ShowIf, bands, caveats; Band.meaning attributed). Source ids prefix "sc-tr-". Then write ${REPO}/composeApp/src/commonTest/kotlin/com/medicalaid/app/content/scores/TraumaScoresTest.kt modelled on AcuteCareScoresTest.kt in the same folder (a few answer combinations → expected band).
B) PROTOCOLS — overwrite ${DATA}/TraumaHeadSpine.kt:
1. id "head-injury" (keywords head injury, TBI, concussion, RTA head injury, GCS, CT head): assessment (link scoreIds gcs, canadian-ct-head); NICE NG232 (2023) CT head indications and timing, patients on anticoagulants, when to image the cervical spine; observation frequency and discharge advice; moderate–severe TBI — avoid hypoxia and hypotension (thresholds by age as sourced, Brain Trauma Foundation 4th edition), intubation threshold, signs of raised ICP and herniation, hyperosmolar therapy (mannitol / hypertonic saline doses as sourced), head elevation, ventilation targets (avoid prolonged hyperventilation), seizure prophylaxis (agent and duration as sourced), tranexamic acid (CRASH-3: eligibility and time window), no corticosteroids (CRASH), anticoagulant reversal, neurosurgical referral criteria. Indian sources (ICMR STW head injury, Neurotrauma Society of India) where available.
2. id "spinal-injury" (keywords spinal injury, cervical spine, C-spine, neck injury, collar, paraplegia, quadriplegia): clinical decision rules (link scoreIds canadian-c-spine, nexus-c-spine — which you create), spinal motion restriction (NICE NG41; current stance on rigid collars as sourced), imaging choice (CT/MRI per NICE NG41), neurogenic shock and MAP targets (as sourced, e.g. AANS/CNS), methylprednisolone controversy as a ProtocolNote (AANS/CNS 2013 vs AOSpine 2017), bladder and pressure-area care, transfer to a spinal unit.`,
  },
  {
    key: 'limb', files: [`${DATA}/TraumaLimbAbdomen.kt`], sourcesVal: 'limbAbdomenSources', protocolsVal: 'limbAbdomenProtocols', category: 'Trauma', prefix: 'tr-la-',
    brief: `1. id "abdominal-pelvic-trauma" (keywords blunt abdomen, stab injury, FAST, eFAST, pelvic fracture, pelvic binder, liver injury, spleen injury): blunt vs penetrating; eFAST role and limits; CT in haemodynamically stable patients; unstable patient pathways (laparotomy, pelvic packing, angioembolisation, REBOA as sourced — WSES/EAST); pelvic binder placement level; non-operative management of solid-organ injury criteria (WSES liver/spleen guidelines); signs of urethral injury before catheterisation; penetrating injuries — do not remove impaled objects; antibiotic and tetanus as sourced.
2. id "open-fractures" (keywords open fracture, compound fracture): BOA/BAPRAS BOAST Open Fractures (2017) and NICE NG37: antibiotics timing and agents (with penicillin-allergy alternative) and duration as sourced; tetanus; photograph, gross contamination removal, saline-soaked dressing, splinting; neurovascular assessment; timing of debridement and soft-tissue cover; vascular injury escalation; ICMR antimicrobial guidance for India where available.
3. id "compartment-syndrome-crush" (keywords compartment syndrome, crush injury, crush syndrome, rhabdomyolysis, building collapse): acute compartment syndrome — clinical features (which are early vs late), pressure-measurement thresholds as sourced, immediate actions (remove dressings/casts, limb at heart level), urgent fasciotomy (BOAST 10 or current BOA standard); crush syndrome — fluids before/at extrication (type, rate as sourced, e.g. ISN Renal Disaster Relief Task Force recommendations), hyperkalaemia, urine-output targets, rhabdomyolysis monitoring (CK), avoid potassium-containing fluids, dialysis needs; NDMA India guidance if relevant.`,
  },
  {
    key: 'wounds', files: [`${DATA}/WoundsBites.kt`], sourcesVal: 'woundsBitesSources', protocolsVal: 'woundsBitesProtocols', category: 'Trauma', prefix: 'tr-wb-',
    brief: `1. id "animal-bite-rabies" (keywords dog bite, monkey bite, cat bite, rabies, ARV, anti-rabies vaccine, RIG, ERIG, HRIG, rabies monoclonal antibody, Rabishield, Twinrab, PEP): India National Guidelines for Rabies Prophylaxis (NRCP/NCDC, latest edition) first, then WHO position paper on rabies vaccines (2018) and WHO expert consultation: WHO exposure categories I/II/III with the action for each; wound care (washing duration with soap and running water, antiseptic, delayed/loose suturing and RIG infiltration first); vaccine schedules — intramuscular (Essen 5-dose and other approved IM regimens) and intradermal (Updated Thai Red Cross) with days and doses as sourced; previously vaccinated persons; rabies immunoglobulin — ERIG and HRIG doses per kg (StepDose with the weight-based dose), rabies monoclonal antibody dose as approved in India, infiltration technique, maximum volume, skin test position (as sourced); when the biting animal can be observed (10-day observation rule as sourced); antibiotic prophylaxis for bite wounds (agent and indications as sourced, e.g. IDSA skin and soft-tissue guideline); tetanus (link the tetanus protocol by name).
2. id "tetanus-prophylaxis" (keywords tetanus, TT injection, Td, TIG, tetanus immunoglobulin, rusty nail, wound): tetanus-prone vs clean wounds definitions; vaccination-history table for vaccine and immunoglobulin (India UIP/MoHFW Td guidance — Td has replaced TT; WHO 2017 tetanus position paper; CDC/ACIP for comparison); TIG dose (prophylaxis vs treatment as sourced); pregnancy; wound care. Note India vs CDC table differences as a ProtocolNote.`,
  },
  {
    key: 'earlypreg', files: [`${DATA}/EarlyPregnancy.kt`], sourcesVal: 'earlyPregnancySources', protocolsVal: 'earlyPregnancyProtocols', category: 'Obstetric', prefix: 'ob-ep-',
    extraVerify: `Also compare with the existing pregnancy page "nvp" (nausea and vomiting) in ${ROOT}/PregnancyConditions.kt and report any contradiction.`,
    brief: `Read the existing pregnancy page id "nvp" in ${ROOT}/PregnancyConditions.kt first and stay consistent with it (report errors, do not edit it).
1. id "ectopic-pregnancy" (keywords ectopic, tubal pregnancy, ruptured ectopic, PUL, pregnancy of unknown location, methotrexate): suspicion in any woman of reproductive age with pain or bleeding; haemodynamic instability → immediate surgery and resuscitation; transvaginal ultrasound and serum β-hCG interpretation as sourced; expectant / medical / surgical criteria; methotrexate regimen (dose by body surface area as sourced — not a StepDose), contraindications checklist and hCG follow-up days; anti-D for Rh-negative women as sourced. Sources: NICE NG126, RCOG/AEPU GTG 21, ACOG PB 193, MoHFW/FOGSI where available.
2. id "miscarriage-septic-abortion" (keywords miscarriage, abortion, incomplete abortion, septic abortion, MVA, misoprostol): heavy bleeding in early pregnancy — resuscitation; incomplete miscarriage options (expectant, misoprostol dose/route by gestation as sourced, manual vacuum aspiration); septic abortion — sepsis recognition, broad-spectrum antibiotics, prompt evacuation; anti-D; MoHFW Comprehensive Abortion Care guidelines, WHO Abortion care guideline 2022, NICE NG126.
3. id "hyperemesis-gravidarum" (keywords hyperemesis, severe vomiting in pregnancy, Wernicke, PUQE): severity (PUQE as sourced), admission criteria, IV fluids (avoid dextrose until thiamine given, as sourced), thiamine, antiemetic escalation and doses, VTE prophylaxis, Wernicke's encephalopathy recognition; RCOG GTG 69 (latest), ACOG PB 189.`,
  },
  {
    key: 'aph', files: [`${DATA}/AntepartumHaemorrhage.kt`], sourcesVal: 'antepartumSources', protocolsVal: 'antepartumProtocols', category: 'Obstetric', prefix: 'ob-aph-',
    brief: `1. id "antepartum-haemorrhage" (keywords APH, bleeding in pregnancy, placenta praevia, abruption, abruptio placentae, vasa praevia, uterine rupture): causes; no digital vaginal examination until placenta praevia is excluded (as sourced); resuscitation and blood; fetal assessment; abruption — DIC risk, delivery decisions; praevia — management by bleeding severity and gestation; vasa praevia; uterine rupture signs; anti-D and Kleihauer as sourced; corticosteroids if preterm. RCOG GTG 63 and 27a/b, MoHFW (SBA / LaQshya / MNH toolkit), FOGSI, WHO MCPC (Managing complications in pregnancy and childbirth, 2nd ed 2017).
2. id "preterm-labour" (keywords preterm labour, PPROM, antenatal steroids, dexamethasone, betamethasone, tocolysis, nifedipine, magnesium neuroprotection): diagnosis; antenatal corticosteroids — gestational window, dose and interval, conditions (WHO 2022 ACS recommendations, MoHFW Operational Guidelines on antenatal corticosteroids); tocolysis — when and which agent and dose (nifedipine regimens as sourced), contraindications; magnesium sulfate for fetal neuroprotection — gestation threshold and regimen; PPROM antibiotics (erythromycin as sourced); NICE NG25, ACOG PB 171/217, WHO.`,
  },
  {
    key: 'labour', files: [`${DATA}/LabourEmergencies.kt`], sourcesVal: 'labourEmergencySources', protocolsVal: 'labourEmergencyProtocols', category: 'Obstetric', prefix: 'ob-lab-',
    brief: `1. id "cord-prolapse" (keywords cord prolapse, cord presentation): recognition; relieve cord compression (manual elevation, positioning, bladder filling as sourced); avoid handling the cord; urgent delivery route and timing; community/transfer actions; RCOG GTG 50, WHO MCPC.
2. id "shoulder-dystocia" (keywords shoulder dystocia, turtle sign, McRoberts): call for help, McRoberts, suprapubic pressure, internal manoeuvres, posterior arm, all-fours, what not to do (fundal pressure, excessive traction), neonatal team, documentation; RCOG GTG 42, ACOG PB 178, WHO MCPC.
3. id "uterine-inversion" (keywords uterine inversion, inverted uterus): recognition, immediate manual replacement, uterotonic timing (stop until replaced, then give), tocolysis/anaesthesia to aid replacement as sourced, shock and PPH management, surgical options; WHO MCPC, RCOG (in GTG 52 or elsewhere as sourced).
4. id "retained-placenta" (keywords retained placenta, manual removal of placenta, MRP): time definitions as sourced; uterotonic and controlled cord traction; manual removal with analgesia/anaesthesia and antibiotic prophylaxis as sourced; what WHO advises against (e.g. ergometrine for retained placenta) as sourced; WHO 2012/2023 PPH recommendations, RCOG GTG 52, MoHFW.`,
  },
  {
    key: 'maternalmed', files: [`${DATA}/MaternalMedical.kt`], sourcesVal: 'maternalMedicalSources', protocolsVal: 'maternalMedicalProtocols', category: 'Obstetric', prefix: 'ob-mm-',
    brief: `1. id "maternal-sepsis" (keywords maternal sepsis, puerperal sepsis, chorioamnionitis, endometritis, septic abortion): WHO definition of maternal sepsis; red flags and early-warning scores as sourced (e.g. MEOWS — text only); antibiotics within the sourced time target, empirical regimens and doses as sourced; source control (retained products, abscess); fluids/vasopressors as in the sepsis protocol; fetal considerations; WHO 2017, RCOG GTG 64a/64b, SMFM 2023, MoHFW/ICMR antimicrobial guidance.
2. id "vte-in-pregnancy" (keywords DVT in pregnancy, PE in pregnancy, LMWH, enoxaparin, thromboprophylaxis): suspected DVT/PE — imaging choices and order (compression ultrasound, CXR, V/Q vs CTPA) as sourced; start LMWH while awaiting results as sourced; treatment-dose LMWH by booking weight (StepDose where weight-based) and prophylactic doses by weight bands as sourced; massive PE; peripartum and neuraxial anaesthesia timing; RCOG GTG 37a/37b, ESC 2019 PE (pregnancy section), ASH 2018 pregnancy VTE.
3. id "dka-in-pregnancy" (keywords DKA in pregnancy, euglycaemic DKA pregnancy): occurs at lower glucose levels, euglycaemic presentation, fetal monitoring, fluids/insulin/potassium as in the DKA protocol with pregnancy-specific points, involve obstetric team; JBDS-IP DKA in pregnancy guideline 2017, ADA 2024 hyperglycaemic crises consensus (pregnancy section).`,
  },
]

const ISSUES = {
  type: 'object',
  properties: {
    checked: { type: 'integer', description: 'number of numeric/clinical claims checked' },
    issues: {
      type: 'array',
      items: {
        type: 'object',
        properties: {
          protocolId: { type: 'string', description: 'protocol or score id' },
          step: { type: 'string' },
          claim: { type: 'string', description: 'the text in the file, quoted' },
          problem: { type: 'string' },
          correction: { type: 'string', description: 'exact replacement wording or action (e.g. remove)' },
          evidence: { type: 'string', description: 'verbatim quote from the source and its URL/locator' },
          severity: { type: 'string', enum: ['critical', 'major', 'minor'] },
        },
        required: ['protocolId', 'step', 'claim', 'problem', 'correction', 'evidence', 'severity'],
      },
    },
  },
  required: ['checked', 'issues'],
}

function writePrompt(g) {
  const main = g.files[0]
  return `${COMMON}

YOUR GROUP: ${g.key}. Assigned files: ${g.files.join(', ')}.
If the file already holds a draft from an earlier interrupted run (not the 8-line placeholder), use it as your starting point: re-check every claim against the sources, correct and complete it, rather than discarding work. Overwrite ${main} (currently a placeholder or an interrupted draft) defining \`internal val ${g.sourcesVal}: List<Source>\` and \`internal val ${g.protocolsVal}: List<Protocol>\`. Every protocol: category = ProtocolCategory.${g.category}. Source id prefix: "${g.prefix}".
${g.brief}

Write each file in one go when ready. FINAL ANSWER (short): protocols (and scores) written; sources and editions used; anything unverified or left out; India vs international differences; any error you noticed in existing app pages.`
}

function verifyPrompt(g, lens) {
  const focus = lens === 'numbers'
    ? `LENS: NUMBERS. Check EVERY number — doses, units, routes, concentrations, infusion rates and durations, repeat intervals and maxima, time windows and targets, gestational-age and fundal-height criteria, blood-loss and BP thresholds, score criteria and cut-offs, vaccine schedules and immunoglobulin doses, StepDose values (minPerKg/maxPerKg/ceilingPerKg/maxTotal/unit/per) — against the cited primary source (current edition). Open the sources yourself (WebFetch/WebSearch/PMC/society and government sites/DailyMed). A claim with no verifiable support is an issue. Flag superseded editions when a newer guideline changes the number.`
    : `LENS: SAFETY AND ATTRIBUTION. Look for: dangerous omissions a casualty or labour-room doctor in India would need (key contraindications, monitoring, antidotes, when NOT to do something the protocol suggests, maxima); evidence status overstated or understated; statements not supported by the cited source; misattribution or wrong edition; numbers taken from the proprietary ATLS manual; unattributed commands where sources differ; wrong or missing citation locators; Kotlin issues that would misrender or fail to compile (unescaped dollar signs or quotes, missing category, unresolved source or score ids). ${g.extraVerify || ''} Open the sources yourself to confirm.`
  return `You are an independent, skeptical reviewer of clinical content in ${g.files.join(', ')} (${g.category === 'Obstetric' ? 'obstetric emergency' : 'trauma'} protocols${g.key === 'headspine' ? ' and clinical decision rules' : ''} for doctors in India). Your job is to find errors before doctors rely on them — assume nothing is correct until you have checked it against the cited source. Read ${ROOT}/protocols/ProtocolModel.kt and ${ROOT}/Model.kt for the data model.

${focus}

Report only real, evidenced problems: each issue must include a verbatim quote from the source (or a clear statement that the source does not contain the claim, with the URL you checked) and an exact correction. Do NOT edit any file. Do NOT run gradle.`
}

function fixPrompt(g, issues) {
  return `You are fixing verified review findings in ${g.files.join(', ')} (a Kotlin Multiplatform clinical reference app). Read ${ROOT}/protocols/ProtocolModel.kt and the file(s) first.

Reviewers reported these issues (JSON):
${JSON.stringify(issues, null, 2)}

For EACH issue: re-check it yourself against the source (WebFetch/WebSearch). Apply the correction only if you confirm it; if the reviewer is wrong, leave the text and say why. Issues about pages outside your files (e.g. an existing pregnancy page): do not edit them — list them in your answer. Keep the structure and style; keep every Step cited; every Source in your files must be cited at least once and every citation must resolve to a Source in the protocols/data files, scores files or ${ROOT}/Sources.kt. Kotlin must stay valid (escape dollar signs as \\$ and inner quotes). Edit ONLY ${g.files.join(', ')}. Do NOT run gradle.

FINAL ANSWER (short): issues applied, issues rejected (with reason), issues outside your files.`
}

const wanted = Array.isArray(args) ? args : GROUPS.map(g => g.key)
const groups = GROUPS.filter(g => wanted.includes(g.key))
log(`Groups: ${groups.map(g => g.key).join(', ')}`)

const results = await pipeline(
  groups,
  (g) => agent(writePrompt(g), { label: `write:${g.key}`, phase: 'Write' }),
  async (writeReport, g) => {
    const lenses = await parallel(['numbers', 'safety'].map(lens => () =>
      agent(verifyPrompt(g, lens), { label: `verify-${lens}:${g.key}`, phase: 'Verify', schema: ISSUES })))
    const issues = lenses.filter(Boolean).flatMap(r => r.issues || [])
    const checked = lenses.filter(Boolean).reduce((n, r) => n + (r.checked || 0), 0)
    log(`${g.key}: ${issues.length} issue(s) from ${checked} checks`)
    return { writeReport, issues, checked }
  },
  async (v, g) => {
    if (!v.issues.length) return { group: g.key, writeReport: v.writeReport, checked: v.checked, issues: [], fixReport: 'No issues found.' }
    const fixReport = await agent(fixPrompt(g, v.issues), { label: `fix:${g.key}`, phase: 'Fix' })
    return { group: g.key, writeReport: v.writeReport, checked: v.checked, issues: v.issues, fixReport }
  },
)

return results
