# ez-vcard: optional dependencies are excluded (see build.gradle.kts)
-dontwarn ezvcard.io.json.**            # JSON serializer (for jCards) not used
-dontwarn com.fasterxml.jackson.**      # Jackson (for jCards) not used
-dontwarn freemarker.**                 # freemarker templating library (for creating hCards) not used
-dontwarn org.jsoup.**                  # jsoup library (for hCard parsing) not used
-keep class ezvcard.property.** { *; }  # keep all vCard properties (created at runtime)
