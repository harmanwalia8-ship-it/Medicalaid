# Contributing

Thank you for helping. Because clinicians rely on this content, changes to medical content follow stricter rules
than changes to code.

## Medical content (doses, thresholds, recommendations, drug profiles)

1. **Cite an allowed source** for every statement — add it to `Sources.kt` (guidelines) or to the drug data file
   (labels), with the exact section, table or recommendation number.
2. **Use your own words.** State the facts briefly; do not paste guideline or label text, tables or figures.
3. **Attribute, don't direct.** Write "MoHFW: start 25 mcg/day", not "Start 25 mcg/day", when the statement is a recommendation.
4. **Show disagreements.** If authoritative sources differ, show both, labelled — do not merge or choose silently.
5. **Clinical review.** A second clinician must review and approve any change to medical content before it is merged.
6. **Run the tests.** `./gradlew :composeApp:testDebugUnitTest` — the content-integrity test fails if anything is
   uncited, cites an unknown source, or if a source is unused.

### Allowed sources
- Government of India (MoHFW, ICMR, NTEP, NCVBDC, NLEM) and ACOG first.
- Then: WHO, CDC, USPSTF, US FDA labels (DailyMed / openFDA), EMA product information, SMFM, ADA, ATA, GINA,
  NHLBI/NAEPP, NICE, RCOG, IDSA, KDIGO, NIH LiverTox and LactMed.
- Commercial references (BNF, Lexicomp, Micromedex, Medscape, UpToDate, Drugs.com) may be **linked** for
  readers but not copied or used as the cited basis.

## Images
Only public-domain or openly licensed images (e.g. CDC PHIL, Wikimedia Commons, CC BY open-access articles),
each with author, licence and source URL recorded. Clinical photos you took yourself need the patient's written
consent and must contain nothing identifying.

## Code
Kotlin Multiplatform + Compose Multiplatform. Keep shared logic in `commonMain` with tests in `commonTest`.
Render screenshots with `./gradlew :composeApp:desktopTest -Pscreenshots` to check UI changes.
