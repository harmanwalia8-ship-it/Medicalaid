# Handoff — where the work stands (22 Nov 2026)

Read this first in a new session, together with `README.md` (what the app is, content rules) and `ROADMAP.md`
(queued work). Branch: `ccr-f183fc69-36wkic`. Last shipped APK: **0.5.0** (versionCode 5).

## Build and check
```
export ANDROID_HOME=/opt/android-sdk
./gradlew :composeApp:testDebugUnitTest          # unit + content-integrity tests
./gradlew :composeApp:assembleRelease            # APK → composeApp/build/outputs/apk/release/
./gradlew :composeApp:desktopTest --tests '*Screenshots*' -Pscreenshots   # PNGs → composeApp/build/screenshots/
```
Version lives in `composeApp/build.gradle.kts` (versionCode/versionName) and `APP_VERSION` in `ui/home/AboutScreen.kt`.

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
  written (3515477); **both reviews still to run** (they were stopped mid-way), then the fixer.
- `WoundsBites.kt` (dog bites/rabies, tetanus) — placeholder: write, review, fix.
- `TraumaLimbAbdomen.kt` (abdominal/pelvic, open fractures, compartment/crush) — placeholder.
- Obstetric batch 2 — placeholders `EarlyPregnancy.kt`, `AntepartumHaemorrhage.kt`, `LabourEmergencies.kt`,
  `MaternalMedical.kt` (briefs in the GROUPS array: earlypreg, aph, labour, maternalmed).

## Next steps, in order
1. Head/spine: two reviews → fixer. 2. Wounds/bites: write → reviews → fixer. 3. Release trauma + head/spine +
   wounds; build **0.5.1** (versionCode 6), README, APK, send. 4. Limb/abdomen. 5. Obstetric batch 2.
6. Ask the user before starting `ROADMAP.md` (child emergencies, infections/antibiotics, fever work-up, steroid
   misuse, paediatric doses, remaining drug phases).

## User preferences
- Clinician decision support for doctors in India (Punjab); sources India first, every statement cited.
- Uncluttered, premium UI; no purple, no pill shapes; side menu, search everywhere.
- Keep ESC content for now (permission needed before public release). Before sharing widely: colleague review,
  signing key, CDSCO check (README checklist).
- Ask before starting big batches; keep usage low.
