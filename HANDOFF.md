# Handoff — where the work stands (23 Nov 2026)

Read this first in a new session, together with `README.md` (what the app is, content rules) and `ROADMAP.md`
(queued work). Branch: `claude/handoff-continuation-ok7mdf` (earlier work: `ccr-f183fc69-36wkic`). Last shipped APK: **0.5.2** (versionCode 7), sent 23 Nov 2026.

## Build and check
```
export ANDROID_HOME=/opt/android-sdk
./gradlew :composeApp:testDebugUnitTest          # unit + content-integrity tests
./gradlew :composeApp:assembleRelease            # APK → composeApp/build/outputs/apk/release/
./gradlew :composeApp:desktopTest --tests '*Screenshots*' -Pscreenshots   # PNGs → composeApp/build/screenshots/
```
Version lives in `composeApp/build.gradle.kts` (versionCode/versionName) and `APP_VERSION` in `ui/home/AboutScreen.kt`.

Fresh cloud container setup (neither survives a new container):
- Android SDK: download `commandlinetools-linux-*_latest.zip` from dl.google.com into `/opt/android-sdk/cmdline-tools/latest`,
  accept licences, then `sdkmanager "platforms;android-36" "build-tools;36.0.0" "platform-tools"`.
- Maven Central rate-limits the shared proxy (HTTP 429). Use Google's mirror via an init script:
  `mkdir -p ~/.gradle/init.d && cp .claude-work/gradle-mirror.init.gradle.kts ~/.gradle/init.d/`
- Signing: release APKs are signed with the container's debug key (`~/.android/debug.keystore`, created by the first
  build). A new container makes a new key, so the user must uninstall the old APK before installing (0.5.0 → 0.5.1
  needed this). 0.5.1 key SHA-256: 2cca6dc7…afdbd28. A stable key is a decision for the user (asked 23 Nov 2026).

## How content is gated
`content/protocols/Protocols.kt` has two lists: `released` (shown) and `inReview` (compiled and tested, never
shown). A group moves to `released` only after the review cycle below. **Scores are not gated** — anything in
`content/scores/data/*.kt` appears in the app, so unreviewed score drafts must sit in `drafts/` before an APK build.

## Review cycle (every new content file)
1. A writer drafts from primary sources, India first (MoHFW/ICMR/FOGSI…), every statement attributed and cited.
2. Two independent reviewers: one checks every number, one checks safety and attribution. Each writes JSON
   findings (`protocolId, step, claim, problem, correction, evidence, severity`).
3. A fixer re-checks each finding against the source before editing.
Writer briefs and exact prompt wording: `.claude-work/obstetric-trauma-protocols.js` (GROUPS array; COMMON,
verifyPrompt, fixPrompt). Run with the Workflow tool if available, otherwise one background agent per step.
The user is on a smaller plan: **one job at a time**.

## State
**Released (in 0.5.2):** everything in 0.5.0 (stroke; 22 poisoning protocols + cocaine use disorder page; all
medical emergencies; obstetric batch 1; burns chart; ABG interpreter) plus trauma (major trauma, haemorrhage, chest,
traumatic arrest), head injury and spinal injury with the Canadian C-spine Rule and NEXUS, animal bites/rabies PEP
and tetanus prophylaxis; (0.5.2) abdominal/pelvic trauma, open fractures, compartment syndrome/crush, and the
EAST 2025 REBOA position added to the released traumatic haemorrhage/arrest protocols; FOGSI 2026 eclampsia notes; teal/square UI with guideline tabs; score panel "Show more".
Review findings are in `.claude-work/reviews/` (headspine-*, wounds-*, limb-*).

**Follow-ups for a clinician (not blocking):** README "Known gaps" — rabies monoclonal antibody doses from trials;
CDC/ACIP tetanus table; MoHFW STG 2025 Ch. 13 trauma-in-pregnancy statements not added (50% more crystalloid,
PaCO₂ 30, perimortem caesarean wording) and 15° vs 30° tilt.

**In review (hidden):**
- `EarlyPregnancy.kt` (ectopic-pregnancy, miscarriage-septic-abortion, hyperemesis-gravidarum) — **written** 23 Nov
  2026, tests pass; numbers review → safety review → fixer still to run (findings → `.claude-work/reviews/earlypreg-*.json`).
  The writer flagged the released "nvp" pregnancy page (PregnancyConditions.kt) against RCOG GTG 69 (2024): ginger
  first-line, thiamine only after 3 weeks of vomiting, no VTE prophylaxis, methylprednisolone cleft caution, an "FDA
  16 mg ondansetron" line cited to ACOG PB 189; and `ob-ecl-mohfw-stg-obg` (Eclampsia.kt) is titled for two chapters
  but is the whole MoHFW STG 2017 O&G document. Let the earlypreg fixer verify and correct these.
- Obstetric batch 2, remaining placeholders: `AntepartumHaemorrhage.kt`, `LabourEmergencies.kt`, `MaternalMedical.kt`
  (briefs in the GROUPS array: aph, labour, maternalmed).

## Next steps, in order
1–3. Done: head/spine and wounds/bites reviewed and fixed; 0.5.1 released and sent (23 Nov 2026).
4. Done: limb/abdomen reviewed and fixed; 0.5.2 released and sent (23 Nov 2026). Trauma section complete.
5. **Obstetric batch 2**, one group at a time: `earlypreg` → `aph` → release 0.5.3; `labour` → `maternalmed` →
   release 0.5.4. Each: write → numbers review → safety review → fixer.
5b. **Infections A–Z next, before child emergencies** (user decision 23 Nov 2026). The user is having Gemini draft
   infection pages from a 0.5.1 repo zip: Batch 2 (D–H: dengue, diphtheria, enteric fever, filariasis, giardiasis,
   gonorrhoea, hepatitis A/E, hepatitis B) is saved at `.claude-work/infections/gemini-batch2-D-H.md`; Batch 1 (A–C:
   acute diarrhoea/cholera, amoebiasis, ascariasis/STH, bacterial meningitis, brucellosis, chickenpox, chikungunya,
   CAP; 21 sources, 7 photos) at `.claude-work/infections/gemini-batch1-A-C.md` — its "Integration Spec" section
   describes the intended model (Population.Infections, ConditionImage, Conditions released/inReview infections,
   Sources registration, ContentIntegrityTest checks, collapsible photo card in ConditionScreen). Overlaps with
   released protocols — bacterial meningitis (NeuroEmergencies) and severe dengue (InfectionEmergencies) — must be
   consistent and cross-linked; reviewers should compare them. User decisions:
   - Treat Gemini's documents as **writer drafts**: convert to app pages, then numbers review → safety review →
     fixer against the primary sources, exactly like protocols. Gemini's "verified" claims are not trusted.
   - **Include the open-licence photos**: verify each Wikimedia licence and author on the file page, show the
     credit and licence under the photo, bundle images offline. Drop any photo whose licence cannot be confirmed.
   - The drafts assume a content model that does not exist yet (`Population.Infections`, `ConditionImage` +
     `Condition.image`, grouped released/in-review infection conditions, an image fetch script). Build it to fit the
     existing Condition model and the app's UI rules, with in-review gating like `Protocols.inReview`.
6. Then `ROADMAP.md`, sections in order (infections already done per 5b) — the user approved on 23 Nov 2026 going straight on without asking
   (child emergencies, infections/antibiotics, fever work-up, steroid misuse, paediatric doses, remaining drug
   phases, smaller items). Skip and list for the user: licensed skin photos, colleague review, signing key, CDSCO.
   **iOS build: do not start without asking the user** (said 23 Nov 2026).

## Release plan (an APK to the user after each major phase)
| Version (code) | Released groups | Notes |
|---|---|---|
| 0.5.1 (6) | trauma + head/spine + wounds/bites | also ships FOGSI 2026 notes, teal UI, score-panel "Show more" fix |
| 0.5.2 (7) | limb/abdomen trauma | trauma complete |
| 0.5.3 (8) | early pregnancy + APH/preterm labour | |
| 0.5.4 (9) | labour-room emergencies + maternal medical (sepsis, VTE, DKA in pregnancy) | obstetric complete |
| 0.6.0 (10) | Infections A–Z from Gemini drafts, reviewed: Batch 1 A–C (when uploaded) + Batch 2 D–H, with photos | user moved ahead of ROADMAP 1 |
| 0.6.1 (11) | child emergencies part 1: arrest/seriously ill child, neonatal resuscitation, sepsis, seizures, DKA | ROADMAP 1 |
| 0.6.2 (12) | child emergencies part 2: asthma/bronchiolitis/croup, diarrhoea, SAM, anaphylaxis/dengue/malaria | ROADMAP 1 |
| 0.7.0 (13) | antibiotics in Drugs + remaining Infections A–Z | ROADMAP 2; split into two APKs if large |
| 0.7.1 (14) | fever work-up | ROADMAP 3 |
| 0.8.0 (15) | steroid misuse + children's doses in Drugs | ROADMAP 4, 5 |
| 0.9.0 (16) | Treatment pages phase 1 (remaining) + phase 2 | ROADMAP 6 |
| 0.9.1 (17) | Treatment pages phase 3 (pregnancy additions) | ROADMAP 6 |
| 0.9.2 (18) | smaller items: CURB-65 mg/dL urea, poisoning batch 2, AHA/ASA 2026 stroke correction (iOS CI build only if the user says yes) | ROADMAP 7 |
At each: move groups to `released`, bump versionCode/versionName and `APP_VERSION`, README, tests, release APK,
send with SendUserFile, commit and push. Move any unreviewed score file to `drafts/` before building.

## Schedule
Scheduled task "Resume Medicalaid work every 5 hours" fires into this session at 4:21, 9:21, 14:21, 19:21 (and 0:21
once its first run adds it) IST. Each run continues the next step through HANDOFF and ROADMAP; after the last APK it
disables itself and sends the user a summary of what still needs them.

## User preferences
- Clinician decision support for doctors in India (Punjab); sources India first, every statement cited.
- Uncluttered, premium UI; no purple, no pill shapes; side menu, search everywhere.
- Keep ESC content for now (permission needed before public release). Before sharing widely: colleague review,
  signing key, CDSCO check (README checklist).
- Ask before starting big batches; keep usage low. (23 Nov 2026: the whole HANDOFF + ROADMAP queue is approved.)
