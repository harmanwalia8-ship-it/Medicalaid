export const meta = {
  name: 'emergency-protocols',
  description: 'Write medical-emergency protocols (groups chosen by args), adversarially verify every number and safety point against sources, apply confirmed fixes',
  phases: [
    { title: 'Write', detail: 'one writer per group, India-first primary sources' },
    { title: 'Verify', detail: 'two independent lenses: numbers, safety/attribution' },
    { title: 'Fix', detail: 'fixer re-confirms each issue against the source before editing' },
  ],
}

const APP = '/home/user/Medicalaid/composeApp/src/commonMain/kotlin/com/medicalaid/app'
const ROOT = APP + '/content'
const DATA = ROOT + '/protocols/data'
const SCRATCH = '/tmp/claude-0/-home-user-Medicalaid/5ca48dd6-74ab-5ae0-9cf9-16fd9a4485a1/scratchpad/emerg'

const SCORE_IDS = 'qsofa gcs nihss abcd2 rosier cha2ds2-vasc has-bled wells-dvt wells-pe perc heart timi-ua-nstemi curb-65 news2 alvarado air glasgow-blatchford child-pugh centor-mcisaac bishop ottawa-ankle ottawa-foot canadian-ct-head'

const COMMON = `You are working on a clinician-facing Kotlin Multiplatform app at /home/user/Medicalaid used by emergency and casualty doctors in India (Punjab). Accuracy matters more than speed. The app summarises guidelines with a citation on every step; it reports what sources say (attributed where sources differ, e.g. "ICMR STW: …", "AHA 2025: …", "ERC 2025: …", "NICE NG217: …"), it does not instruct. It is decision support for qualified clinicians, adult patients unless a step says otherwise.

Read first:
- ${ROOT}/protocols/ProtocolModel.kt (Protocol(id,title,summary,keywords,category,steps,notes,citations) — category is REQUIRED; Step(title,timing,points,checklistTitle,checklist,tool,scoreIds,doses,citations); StepDose(label, dose: WeightDose, note, maxTotal); ProtocolTool {OnsetClock, ThrombolysisDose, BurnsChart, AcidBase}; ProtocolNote; ProtocolCategory {Cardiac, Respiratory, Neuro, Metabolic, Infection, Environmental, Poisoning})
- ${DATA}/Stroke.kt (complete worked example — match its structure, attribution style, Source entries and citation locators)
- ${ROOT}/Model.kt (Source(id, authority, document, title, year, url, status); Authority enum incl. MoHFW, ICMR, NDMA, ISCCM, CSI (India), WHO, AHA, ESC, ERC, RCUK, NICE, ADA, JBDS, EndoSoc, ESE, ATA, GINA, GOLD, BTS, AES, ILAE, AHS, ESCMID, IDSA, ACG, WAO, ABA, ISBI, WMS, UKKA, SSC, KDIGO, ACEP, FDA, Paper; WeightDose(minPerKg, maxPerKg, unit, per, ceilingPerKg); cite(sourceId, locator))
- ${ROOT}/Sources.kt and the other files in ${DATA}/ and ${ROOT}/*.kt — existing Source ids may be cited (e.g. ESC heart-failure source in HeartConditions.kt); do not duplicate a document that already has a Source, cite its id instead.

SOURCES — India first, then international: ICMR Standard Treatment Workflows (https://www.icmr.gov.in/standard-treatment-workflows-stws), MoHFW / NHM / NCVBDC / NCDC / NPCCHH / NDMA documents, ICMR antimicrobial treatment guidelines, AIIMS/ISCCM/CSI guidance; then the current editions of the international guidelines (check for 2025–2026 updates — e.g. AHA and ERC 2025 resuscitation guidelines — and use the latest). ESC guidelines may be used: Authority.ESC with document "ESC guideline — Eur Heart J <year;vol:pages>". Verify EVERY dose, threshold, time window and lab cut-off from the primary document with WebSearch/WebFetch (PMC/PubMed full texts, society websites, DailyMed/openFDA for product facts). Never fill from memory; if you cannot verify something, leave it out and list it in your report. Units: mg/dL for glucose (mmol/L in brackets where the source uses it), mmol/L for electrolytes. Source ids: lowercase-hyphenated with your group prefix (given below), unique across all files in ${DATA}/. Official URLs (DOI acceptable), https only. Save downloads only under ${SCRATCH}/.

WRITING RULES: 5–9 steps per protocol, 2–6 points per step (each ≤ ~25 words), attributed where sources differ; \`timing\` on time-critical steps; \`checklist\` for criteria, contraindications, red flags or don'ts; \`doses\` (StepDose) for every weight-based dose with maxTotal when the source caps it (unit may be "mg", "mcg", "units", "mL"; per e.g. "IV bolus" or "per hour"), fixed doses in points; \`scoreIds\` to link existing scores where relevant (available ids: ${SCORE_IDS}); \`tool\` where named in your brief. Every Step has citations with locators (section/table/figure/recommendation number). Keywords: include abbreviations and terms Indian clinicians use. ProtocolNotes for India vs international differences and real evidence controversies. Kotlin must be valid: escape any dollar sign as \\$, escape double quotes inside strings; imports as needed (Authority, Source, WeightDose, cite, Protocol, ProtocolCategory, ProtocolTool, Step, StepDose, ProtocolNote). Edit ONLY your assigned file(s). Do NOT run gradle.`

const GROUPS = [
  {
    key: 'endocrine', file: 'EmergencyEndocrine.kt', sourcesVal: 'endocrineEmergencySources', protocolsVal: 'endocrineEmergencyProtocols', category: 'Metabolic', prefix: 'em-endo-',
    brief: `1. id "dka" — Diabetic ketoacidosis (adult; keywords DKA, ketoacidosis, ketones, euglycaemic DKA, SGLT2): diagnostic and severity criteria (ADA/EASD/JBDS/AACE/DTS 2024 hyperglycaemic crises consensus; JBDS-IP; ICMR STW if available); fluids (first hour and after); potassium before and during insulin, when to delay insulin; fixed-rate IV insulin (StepDose in units/kg/h) and whether a bolus is used; when to add dextrose; bicarbonate and phosphate criteria; resolution criteria; transition to subcutaneous insulin; precipitants; cerebral oedema and hypokalaemia/hypoglycaemia warnings; SGLT2-inhibitor euglycaemic DKA. Put tool = ProtocolTool.AcidBase on the assessment step.
2. id "hhs" — Hyperosmolar hyperglycaemic state: criteria, calculated osmolality, fluid-first approach, safe rates of fall of glucose/sodium/osmolality, when and at what rate insulin starts (JBDS vs ADA consensus differences as a ProtocolNote), VTE prophylaxis, foot checks.
3. id "hypoglycaemia" — adult hypoglycaemia (levels 1–3), conscious vs unconscious treatment (oral carbohydrate amount, IV dextrose concentrations/volumes, IM/SC/nasal glucagon), recheck interval, sulfonylurea-induced prolonged hypoglycaemia (octreotide as sourced), admission criteria.
4. id "adrenal-crisis" — recognition, hydrocortisone dose and route and subsequent dosing, fluids, don't delay for tests, triggers, steroid-dependent patients (Endocrine Society 2016; Society for Endocrinology / ESE guidance; any Indian source).
5. id "thyroid-storm" — diagnostic tools (Burch–Wartofsky thresholds, JTA criteria — as text), beta-blocker, thionamide choice and doses, iodine timing relative to thionamide, glucocorticoid, cooling and supportive care, what to avoid (ATA 2016 hyperthyroidism guideline, JTA 2016).
6. id "myxoedema-coma" — recognition, IV levothyroxine loading and maintenance, glucocorticoid first, liothyronine position, rewarming and supportive care (ATA 2014 hypothyroidism guideline).`,
  },
  {
    key: 'acidbase', file: 'AcidBaseDisorders.kt', sourcesVal: 'acidBaseSources', protocolsVal: 'acidBaseProtocols', category: 'Metabolic', prefix: 'em-ab-',
    brief: `Four protocols, each with a first step "Read the gas" that has tool = ProtocolTool.AcidBase (an ABG interpreter: primary disorder, expected compensation, anion gap, albumin-corrected gap, delta ratio):
1. id "metabolic-acidosis" — stepwise approach; high vs normal anion gap causes; osmolar gap (toxic alcohols — refer to the toxic alcohol protocol by name); lactate; treatment of cause; IV sodium bicarbonate — indications and evidence (e.g. BICAR-ICU RCT) as sourced; hyperchloraemia from saline.
2. id "metabolic-alkalosis" — causes; urine chloride to separate chloride-responsive vs resistant (thresholds as sourced); correct volume, potassium, magnesium; acetazolamide/HCl indications as sourced.
3. id "respiratory-acidosis" — acute vs chronic; causes (COPD, opioids/sedatives, neuromuscular, obesity hypoventilation); controlled oxygen targets; NIV criteria (BTS/ERS, BTS/ICS acute hypercapnic respiratory failure guideline) and intubation triggers; opioid reversal; avoid excessive oxygen.
4. id "respiratory-alkalosis" — causes (hypoxaemia, PE, sepsis, salicylate, pregnancy, liver failure, anxiety only after exclusion); treat cause; what sources say about rebreathing.
Use physiology reviews (e.g. NEJM/Clin J Am Soc Nephrol reviews), BTS/ERS guidelines and Indian sources if available.`,
  },
  {
    key: 'electrolytes', file: 'Electrolytes.kt', sourcesVal: 'electrolyteSources', protocolsVal: 'electrolyteProtocols', category: 'Metabolic', prefix: 'em-lyte-',
    brief: `1. id "hyperkalaemia" — severity bands and ECG changes; IV calcium (gluconate vs chloride doses, repeat); insulin–glucose (doses, hypoglycaemia monitoring schedule); nebulised salbutamol; potassium binders (sodium zirconium cyclosilicate, patiromer, calcium/sodium polystyrene sulfonate as available in India); sodium bicarbonate role; dialysis indications (UK Kidney Association 2023 guideline, RCUK/ERC special circumstances, KDIGO if relevant).
2. id "hypokalaemia" — severity; oral vs IV replacement; IV potassium concentration and rate limits (peripheral vs central) and monitoring; magnesium co-repletion; causes.
3. id "hyponatraemia" — acute vs chronic, severe symptoms; hypertonic (3%) saline bolus regimen and targets; correction limits per 24 h and high-risk patients for osmotic demyelination; managing overcorrection; diagnostic approach (osmolality, urine osmolality and sodium); European 2014 (ESE/ESICM/ERBP) vs US expert panel 2013 differences as ProtocolNote.`,
  },
  {
    key: 'cardiac', file: 'CardiacEmergencies.kt', sourcesVal: 'cardiacEmergencySources', protocolsVal: 'cardiacEmergencyProtocols', category: 'Cardiac',
    prefix: 'em-card-',
    brief: `1. id "stemi" — Acute STEMI (keywords MI, heart attack, STEMI, ST elevation, thrombolysis, streptokinase, tenecteplase, primary PCI, pharmaco-invasive): ECG criteria; system time goals (first medical contact/door-to-balloon and door-to-needle — state exactly as each guideline defines them); aspirin and P2Y12 loading doses; anticoagulation; reperfusion choice (primary PCI vs fibrinolysis when PCI cannot be done in time); fibrinolytics used in India — streptokinase, tenecteplase (weight bands, half dose in elderly where sourced), reteplase/alteplase as sourced; absolute/relative contraindications checklist; pharmaco-invasive strategy timing and rescue PCI; ICMR STW on STEMI and Cardiological Society of India / STEMI India guidance; ESC 2023 ACS and ACC/AHA 2025 ACS guidelines.
2. id "nste-acs" — high-sensitivity troponin pathways (0/1 h, 0/2 h) as sourced; risk scores (link scoreIds heart, timi-ua-nstemi); antithrombotic loading; anticoagulant (fondaparinux/enoxaparin/UFH as sourced); timing of invasive strategy; ESC 2023 vs ACC/AHA 2025.
3. id "acute-heart-failure" — acute pulmonary oedema and cardiogenic shock: oxygen thresholds, IV loop diuretic dosing, nitrates and BP thresholds, NIV/CPAP, morphine position, inotropes/vasopressors, causes (CHAMPIT or as sourced). Cite the existing ESC 2026 HF source id in HeartConditions.kt where applicable.
4. id "hypertensive-emergency" — definition (acute target-organ damage) vs urgency; BP lowering targets and time course; compelling conditions with different targets (aortic dissection, pre-eclampsia, acute stroke, acute pulmonary oedema, ACS); IV agents available in India (labetalol, nitroglycerin, esmolol, nicardipine, nitroprusside) with doses as sourced; avoid sublingual nifedipine if sourced. ACC/AHA (2017 and 2025 guidelines), ESC 2024 BP guideline, ISH 2020.
5. id "aortic-dissection" — suspicion (ADD-RS as text), imaging, anti-impulse therapy targets (HR and SBP) and sequence (beta-blocker before vasodilator), type A vs B management, what to avoid (anticoagulation/thrombolysis). ACC/AHA 2022 aortic disease guideline, ESC 2024 peripheral arterial and aortic diseases.`,
  },
  {
    key: 'resus', file: 'Resuscitation.kt', sourcesVal: 'resuscitationSources', protocolsVal: 'resuscitationProtocols', category: 'Cardiac', prefix: 'em-resus-',
    brief: `Use the CURRENT AHA (2025) and ERC (2025) adult guidelines — verify publication and content; ICMR/Indian Resuscitation Council (IRC) guidance if available.
1. id "cardiac-arrest" — adult ALS: high-quality CPR parameters; shockable vs non-shockable sequence; defibrillation energy; adrenaline dose and timing (AHA vs ERC differences); amiodarone/lidocaine doses; airway and capnography; reversible causes (4 Hs and 4 Ts as checklist); ECPR/termination where sourced; post-ROSC care targets (oxygenation, MAP, temperature control/fever prevention, PCI indications).
2. id "tachycardia-with-pulse" — adverse features; synchronised cardioversion energies; narrow regular (vagal manoeuvres incl. modified Valsalva, adenosine doses), irregular narrow (AF rate/rhythm), broad complex (amiodarone dose and duration; procainamide where sourced); torsades (magnesium).
3. id "bradycardia" — adverse features; atropine dose, repeat and maximum (AHA vs ERC); second-line drugs (adrenaline/dopamine/isoprenaline infusions as sourced); transcutaneous pacing; causes to treat (hyperkalaemia, drugs — beta-blocker/calcium-channel blocker/digoxin, inferior MI).`,
  },
  {
    key: 'respiratory', file: 'RespiratoryEmergencies.kt', sourcesVal: 'respiratoryEmergencySources', protocolsVal: 'respiratoryEmergencyProtocols', category: 'Respiratory', prefix: 'em-resp-',
    brief: `1. id "acute-severe-asthma" — status asthmaticus (keywords status asthmaticus, acute asthma, wheeze): severity features (moderate/acute severe/life-threatening/near-fatal thresholds incl. PEF %, SpO2, PaCO2) per BTS/NICE/SIGN 2024 and GINA 2025; oxygen target; SABA doses (nebulised/MDI-spacer, back-to-back), ipratropium, systemic corticosteroid dose/duration; IV magnesium sulfate dose; aminophylline position; ICU/intubation criteria; discharge criteria; ICMR STW for asthma.
2. id "copd-exacerbation" — GOLD 2026 report (verify edition) and NICE NG115: controlled oxygen target; bronchodilators; prednisolone dose/duration; antibiotic indications; NIV criteria (put tool = ProtocolTool.AcidBase on that step); ICMR STW for COPD if available.
3. id "pulmonary-embolism" — suspected PE: clinical probability (link scoreIds wells-pe, perc), D-dimer and age adjustment, imaging; risk stratification (high/intermediate/low); anticoagulation (UFH/LMWH/DOAC doses; UFH weight-based bolus and infusion as StepDose); systemic thrombolysis for high-risk PE (alteplase regimens and contraindications); rescue options. ESC 2019 PE guideline and any newer AHA/ACC acute PE guideline (verify — use the latest).
4. id "anaphylaxis" — diagnostic criteria (WAO 2020), IM adrenaline dose by weight (StepDose, maxTotal), site and repeat interval, positioning, fluids, refractory anaphylaxis (adrenaline infusion), adjuncts (antihistamines/steroids — status as sourced), biphasic reactions and observation, discharge (autoinjector, referral). RCUK 2021, WAO 2020, EAACI 2021/2025, ICMR/Indian guidance if any.`,
  },
  {
    key: 'neuro', file: 'NeuroEmergencies.kt', sourcesVal: 'neuroEmergencySources', protocolsVal: 'neuroEmergencyProtocols', category: 'Neuro', prefix: 'em-neuro-',
    brief: `1. id "status-epilepticus" — (keywords status epilepsy, status epilepticus, fits, seizure not stopping, convulsions) timed phases (ILAE operational t1/t2; AES 2016 algorithm; NICE NG217; ICMR STW): stabilisation, glucose, thiamine before glucose where sourced; first-line benzodiazepines (IM midazolam by weight band, IV lorazepam, IV diazepam, buccal/intranasal midazolam) with doses as StepDose and maxima; second-line (levetiracetam, fosphenytoin/phenytoin, valproate, phenobarbital) weight-based with maxima as StepDose, ESETT trial status; refractory/super-refractory (anaesthetic infusions in ICU); eclampsia (magnesium — refer to pregnancy hypertension page by name); non-convulsive status and EEG.
2. id "migraine-acute" — acute migraine in the emergency department: red flags for secondary headache (checklist, e.g. SNNOOP10 as sourced); recommended parenteral treatments (AHS 2016 ED guideline — metoclopramide, prochlorperazine, SC sumatriptan; dexamethasone to prevent recurrence), what to avoid (opioids, butalbital) as sourced; NICE CG150; Indian sources if available.
3. id "bacterial-meningitis" — suspicion; blood cultures and antibiotics without delay (time target as sourced); when CT should precede LP; empirical antibiotics by age/risk (Listeria cover), doses; adjunctive dexamethasone dose/timing/duration; chemoprophylaxis of contacts; ESCMID 2016, NICE NG240 (2024), IDSA, ICMR antimicrobial treatment guidelines.
4. id "subarachnoid-haemorrhage" — thunderclap headache; Ottawa SAH rule (as text); CT timing and sensitivity, LP/xanthochromia; BP management, nimodipine dose/duration, anticoagulant reversal, seizure management, urgent neurosurgical referral; AHA/ASA 2023 aSAH guideline, NICE NG228.`,
  },
  {
    key: 'environmental', file: 'Environmental.kt', sourcesVal: 'environmentalSources', protocolsVal: 'environmentalProtocols', category: 'Environmental', prefix: 'em-env-',
    brief: `1. id "burns" — (keywords burns, scald, fire, TBSA, Parkland, rule of nines, Lund Browder) first aid (cool running water — duration and time limit as sourced); primary survey; inhalation injury signs checklist and airway; estimating TBSA (Lund–Browder, rule of nines, palm method — what to exclude, e.g. superficial erythema) — put tool = ProtocolTool.BurnsChart on that step; formal fluid resuscitation thresholds (adult and child %TBSA); formulas (Parkland 4 mL/kg/%TBSA vs ABA starting estimates 2 mL adults / 3 mL children / electrical as sourced), half in first 8 h from time of burn; urine-output targets; over-resuscitation risk; children's maintenance fluids; escharotomy; analgesia, tetanus; referral criteria to a burns unit; CO and cyanide (refer to the carbon monoxide protocol by name). India: MoHFW National Programme for Prevention and Management of Burn Injuries (NPPMBI) guidelines, ICMR STW for burns; ABA 2023/2024 burn-shock resuscitation guideline; ISBI practice guidelines 2016/2018.
2. id "heat-stroke" — (keywords heat stroke, loo, heat wave, hyperthermia) definitions (classic vs exertional), cooling methods ranked as sourced (cold-water immersion etc.), cooling target and when to stop, what not to use (antipyretics), complications (rhabdomyolysis, AKI, DIC, hypoglycaemia); MoHFW/NPCCHH heat-related illness guidelines and NDMA heat-wave guidelines; Wilderness Medical Society heat illness guideline (latest).
3. id "electrical-injury" — high vs low voltage and lightning; cardiac monitoring indications (who can be discharged after a normal ECG, as sourced); deep tissue injury, compartment syndrome, rhabdomyolysis and urine-output target; burns fluid formulas underestimate; lightning triage. Use the best available guidance (ERC/AHA special circumstances, reviews, Indian sources).`,
  },
  {
    key: 'infection', file: 'InfectionBleeding.kt', sourcesVal: 'infectionEmergencySources', protocolsVal: 'infectionEmergencyProtocols', category: 'Infection', prefix: 'em-inf-',
    brief: `1. id "sepsis" — sepsis and septic shock (keywords sepsis, septic shock, SIRS, qSOFA): screening (link scoreIds qsofa, news2 — note SSC advice against qSOFA alone); antibiotic timing by probability/shock; cultures; lactate; initial crystalloid volume (StepDose mL/kg) and balanced crystalloids; MAP target; norepinephrine first-line and peripheral use; vasopressin; corticosteroids criteria and dose; source control. Surviving Sepsis Campaign 2021 (and any 2026 update — verify), ICMR antimicrobial treatment guidelines / ICMR STW sepsis for empirical choices in India.
2. id "upper-gi-bleed" — risk stratification (link scoreIds glasgow-blatchford, child-pugh); resuscitation and restrictive transfusion thresholds; PPI before/after endoscopy; endoscopy timing; suspected variceal bleed — vasoactive drug (terlipressin/octreotide) doses, antibiotic prophylaxis, erythromycin before endoscopy; anticoagulant/antiplatelet management. ACG 2021, ESGE 2021, International Consensus 2019, Baveno VII 2022, NICE CG141, Indian (INASL/SGEI) guidance.
3. id "dengue-severe" — warning signs and severe dengue criteria; NCVBDC (MoHFW) National Guidelines for Clinical Management of Dengue (latest edition) fluid regimens for compensated and hypotensive shock (StepDose mL/kg per hour / bolus), haematocrit-guided titration; platelet transfusion thresholds (no prophylactic transfusion except as sourced); avoid NSAIDs/IM injections/steroids as sourced; WHO dengue guidelines.
4. id "severe-malaria" — WHO severe malaria criteria; IV/IM artesunate dose and schedule (adult and small-child doses as StepDose), follow-on ACT; quinine alternative; hypoglycaemia monitoring; supportive care; NCVBDC National Drug Policy / Guidelines for diagnosis and treatment of malaria in India (latest), WHO Guidelines for malaria (latest).`,
  },
  {
    key: 'tools', file: 'ToolSources.kt', category: null, prefix: 'tool-',
    customWrite: true,
    brief: null,
  },
]

const TOOLS_FILES = `${DATA}/ToolSources.kt, ${APP}/tools/Burns.kt, ${APP}/tools/AcidBase.kt, ${APP}/ui/tools/EmergencyTools.kt (text strings only) and /home/user/Medicalaid/composeApp/src/commonTest/kotlin/com/medicalaid/app/tools/BurnsAcidBaseTest.kt`

const toolsWritePrompt = `${COMMON}

TASK (calculator sources): The app has two calculators whose constants and wording must match citable sources: ${APP}/tools/Burns.kt (Lund–Browder percentages per region for six age columns <1, 1–4, 5–9, 10–14, 15 y, adult; split of each region between front and back views; Parkland 4 mL/kg/%TBSA; ABA/ABLS adult starting estimate 2 mL/kg/%TBSA; half of the 24 h volume in the first 8 h from the time of burn, the rest over 16 h) and ${APP}/tools/AcidBase.kt (pH/PaCO2/HCO3 thresholds; Winter's formula; metabolic-alkalosis expected PaCO2; acute/chronic respiratory compensation slopes; anion gap without potassium and its normal value; albumin correction factor; delta-ratio bands and meanings). The UI is ${APP}/ui/tools/EmergencyTools.kt. Draft sources are in ${DATA}/ToolSources.kt with ids tool-lund-browder, tool-burns-fluids, tool-acid-base (ids must stay; you may add more tool- sources and cite them by editing the Citation lists in EmergencyTools.kt).
1. Find and open the primary or authoritative sources (Lund & Browder 1944 original chart or an authoritative reproduction; the current American Burn Association burn-shock resuscitation guideline; an authoritative acid–base reference such as Berend et al. NEJM 2014 or equivalent with the exact formulas). Replace each draft Source with verified bibliographic data, a working https URL and no "Draft" status; choose the correct Authority (ABA, ISBI, Paper…).
2. Check every constant and every user-facing string in these files against those sources. Where a constant differs from the cited source, correct the constant AND the matching expectations in BurnsAcidBaseTest.kt (keep the column-sum tests). If sources legitimately differ (e.g. chronic compensation slopes), keep the cited source's value and note it in your report.
Edit ONLY ${TOOLS_FILES}. Do NOT run gradle. FINAL ANSWER (short): sources used, constants confirmed, constants changed (old → new, with quote), anything unverified.`

function writePrompt(g) {
  if (g.customWrite) return toolsWritePrompt
  return `${COMMON}

YOUR GROUP: file ${DATA}/${g.file} (currently a placeholder or an interrupted draft). If the file already holds a draft from an earlier interrupted run (not the 8-line placeholder), use it as your starting point: re-check every claim against the sources, correct and complete it, rather than discarding work. Write it defining \`internal val ${g.sourcesVal}: List<Source>\` and \`internal val ${g.protocolsVal}: List<Protocol>\`. Every protocol: category = ProtocolCategory.${g.category}. Source id prefix: "${g.prefix}".
Protocols:
${g.brief}

Write the file in one go when ready. FINAL ANSWER (short): protocols written; sources and editions used; anything unverified or left out; India vs international differences.`
}

const ISSUES = {
  type: 'object',
  properties: {
    checked: { type: 'integer', description: 'number of numeric/clinical claims checked' },
    issues: {
      type: 'array',
      items: {
        type: 'object',
        properties: {
          protocolId: { type: 'string' },
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

function targetOf(g) {
  return g.customWrite ? `the calculator files ${TOOLS_FILES}` : `${DATA}/${g.file} (medical-emergency protocols for emergency doctors in India)`
}

function verifyPrompt(g, lens) {
  const focus = lens === 'numbers'
    ? `LENS: NUMBERS. Check EVERY number — doses, mg/kg, units/kg/h, maximums, units, routes, concentrations, infusion rates and durations, repeat intervals, time windows and targets, lab thresholds, score cut-offs, formula constants, StepDose values (minPerKg/maxPerKg/ceilingPerKg/maxTotal/unit/per) — against the cited primary source (current edition). Open the sources yourself (WebFetch/WebSearch/PMC/society sites/DailyMed). A claim with no verifiable support is an issue. Also flag a superseded edition when a newer guideline changes the number.`
    : `LENS: SAFETY AND ATTRIBUTION. Look for: dangerous omissions a casualty doctor would need (key contraindications, monitoring, when NOT to do something the protocol suggests, hypoglycaemia/hypokalaemia risks, extravasation, dose maxima); evidence status overstated or understated; statements not supported by the cited source; misattribution or wrong edition; unattributed commands where sources differ; India-relevant availability problems the source mentions; wrong or missing citation locators; Kotlin issues that would misrender or fail to compile (unescaped dollar signs or quotes, missing category, unresolved ids). Open the sources yourself to confirm.`
  return `You are an independent, skeptical reviewer of clinical content in ${targetOf(g)}. Your job is to find errors before doctors rely on them — assume nothing is correct until you have checked it against the cited source. Read ${ROOT}/protocols/ProtocolModel.kt and ${ROOT}/Model.kt for the data model.

${focus}

Report only real, evidenced problems: each issue must include a verbatim quote from the source (or a clear statement that the source does not contain the claim, with the URL you checked) and an exact correction. Do NOT edit any file. Do NOT run gradle.`
}

function fixPrompt(g, issues) {
  const scope = g.customWrite ? `Edit ONLY ${TOOLS_FILES}; keep code constants, test expectations and UI strings consistent with each other.` : `Edit ONLY ${DATA}/${g.file}.`
  return `You are fixing verified review findings in ${targetOf(g)} (a Kotlin Multiplatform clinical reference app). Read ${ROOT}/protocols/ProtocolModel.kt and the file(s) first.

Reviewers reported these issues (JSON):
${JSON.stringify(issues, null, 2)}

For EACH issue: re-check it yourself against the source (WebFetch/WebSearch). Apply the correction only if you confirm it; if the reviewer is wrong, leave the text and say why. Keep the structure and style; keep every Step cited; every Source in the file must be cited at least once and every citation must resolve to a Source in the protocols/data files or ${ROOT}/Sources.kt. Kotlin must stay valid (escape dollar signs as \\$ and inner quotes). ${scope} Do NOT run gradle.

FINAL ANSWER (short): issues applied, issues rejected (with reason).`
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
