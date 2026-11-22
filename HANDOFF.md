# Handoff — where the work stands (22 Nov 2026)

Read this first in a new session, together with `README.md` (what the app is, content rules) and `ROADMAP.md`
(queued work). Branch: `claude/handoff-continuation-ok7mdf` (earlier work: `ccr-f183fc69-36wkic`). Last shipped APK: **0.5.0** (versionCode 5).

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
**Released (in 0.5.0):** stroke; 22 poisoning protocols + cocaine use disorder page; all medical emergencies
(endocrine, acid–base, electrolytes, cardiac, cardiac arrest/arrhythmias, respiratory, neuro, environmental incl.
burns, infection/bleeding); obstetric batch 1 (eclampsia, PPH, maternal collapse/trauma in pregnancy);
burns chart and ABG interpreter (usual ranges, StatPearls; compensation per Berend).

**Done since 0.5.0, not yet in an APK:** FOGSI 2026 notes in the eclampsia protocols (commit ae6d4af); UI change —
guideline tabs on protocol pages ("All" default), teal accent instead of purple, square-ish shapes (66f171b).

**In review (hidden):**
- `Trauma.kt` (major trauma, haemorrhage, chest, traumatic arrest) — reviewed and fixed (f4b4d36). Release it only
  together with head/spine and wounds, because it links to them.
- `TraumaHeadSpine.kt` + `scores/data/TraumaScores.kt` + `TraumaScoresTest.kt` (Canadian C-spine, NEXUS) —
  **reviewed and fixed** (23 Nov 2026): numbers review 2 minor, safety review 3 major + 11 minor, all 16 confirmed
  and applied (findings in `.claude-work/reviews/headspine-*.json`); tests pass. The C-spine scores are live in the
  app (scores are not gated) and now reviewed. Release the protocols with trauma + wounds in 0.5.1.
- `WoundsBites.kt` (dog bites/rabies, tetanus) — placeholder: write, review, fix.
- `TraumaLimbAbdomen.kt` (abdominal/pelvic, open fractures, compartment/crush) — placeholder.
- Obstetric batch 2 — placeholders `EarlyPregnancy.kt`, `AntepartumHaemorrhage.kt`, `LabourEmergencies.kt`,
  `MaternalMedical.kt` (briefs in the GROUPS array: earlypreg, aph, labour, maternalmed).

## Next steps, in order
1. ~~Head/spine: two reviews → fixer~~ (done). 2. Wounds/bites: write → reviews → fixer. 3. Release trauma + head/spine +
   wounds; build **0.5.1** (versionCode 6), README, APK, send. 4. Limb/abdomen. 5. Obstetric batch 2.
6. Then `ROADMAP.md`, sections in order — the user approved on 23 Nov 2026 going straight on without asking
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
| 0.6.0 (10) | child emergencies part 1: arrest/seriously ill child, neonatal resuscitation, sepsis, seizures, DKA | ROADMAP 1 |
| 0.6.1 (11) | child emergencies part 2: asthma/bronchiolitis/croup, diarrhoea, SAM, anaphylaxis/dengue/malaria | ROADMAP 1 |
| 0.7.0 (12) | antibiotics in Drugs + Infections A–Z | ROADMAP 2; split into two APKs if large |
| 0.7.1 (13) | fever work-up | ROADMAP 3 |
| 0.8.0 (14) | steroid misuse + children's doses in Drugs | ROADMAP 4, 5 |
| 0.9.0 (15) | Treatment pages phase 1 (remaining) + phase 2 | ROADMAP 6 |
| 0.9.1 (16) | Treatment pages phase 3 (pregnancy additions) | ROADMAP 6 |
| 0.9.2 (17) | smaller items: CURB-65 mg/dL urea, poisoning batch 2, AHA/ASA 2026 stroke correction (iOS CI build only if the user says yes) | ROADMAP 7 |
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
