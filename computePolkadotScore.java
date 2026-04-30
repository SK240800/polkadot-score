public final class AngelicaPolkadotScore {
    private AngelicaPolkadotScore() {}

    public static int computePolkadotScore(String asciiArt) {
        String[] lines = asciiArt.split("\\R", -1);

        int eyeLineIndex = -1;
        for (int i = 0; i < (lines.length + 1) / 2; i++) {
            int bullets = 0;
            for (int j = 0; j < lines[i].length(); j++) {
                if (lines[i].charAt(j) == '•') {
                    bullets++;
                }
            }
            if (bullets >= 2) {
                eyeLineIndex = i;
                break;
            }
        }

        if (eyeLineIndex == -1) {
            throw new IllegalArgumentException("Could not find Angelica's pupils.");
        }

        int pupilCharCount = 0;
        for (int j = 0; j < lines[eyeLineIndex].length(); j++) {
            if (lines[eyeLineIndex].charAt(j) == '•') {
                pupilCharCount++;
            }
        }

        if (eyeLineIndex + 1 >= lines.length) {
            throw new IllegalArgumentException("Could not find Angelica's lips.");
        }

        String lipLine = lines[eyeLineIndex + 1];
        int lipStart = -1;
        int lipEnd = -1;
        for (int j = 0; j < lipLine.length(); j++) {
            if (lipLine.charAt(j) == ';') {
                if (lipStart == -1) {
                    lipStart = j;
                }
                lipEnd = j;
            }
        }

        if (lipStart == -1 || lipEnd == -1) {
            throw new IllegalArgumentException("Could not determine the lips range.");
        }

        int inside = 0;
        int outside = 0;

        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'O') {
                    if (j >= lipStart && j <= lipEnd) {
                        inside++;
                    } else {
                        outside++;
                    }
                }
            }
        }

        return outside + inside * pupilCharCount;
    }

    public static void main(String[] args) {
        String art = """
                      ,   ,-',
                ,', ,'  ','  ,'   ÅÑGË£ÏÇÄ †(–)Ë ßRÄ†
              '-',  '      ,'
                  ' -,    ',
                      ' -, ',                                         , - - -,
                     ('''''' ®'''''''')                        ,,,,,    ,-' -,''''''''',
                      ` ~„''`„~ '                          ',  ,', -' , -,'' '''''''''',
                          "„  " - „                   „ - ",®,-'     `~~' '''''''
                         „"         " „         „ - "      ,',,,',
                       „-" " " " " " " ~~~~~~" - „       ,'
                    „" –,'' ~ ,       • ; •          "      "„
                    """";      ' - , ,  ; , , , - '' ' ' -,_ ', ',
                   , -' ' ',           ,'    ,'             ',~', ',
                 ,'         ' - , ,()' /\\    ',          (),'¯ ,'   `¸`;
                 ',                            ` ` ` ` ` `      ,-,,,-'
                   '-,                                  ,¬  ,-'
                      ' -, ~            ~~~~~~' ' ` ,-'
                          `~-,,,,,,,      ,,,,,,,,,,-~'
              ('('('(,,,              ;    ;                •Å(V)åö•
               '-, '-,'''      ,-';`,`'ˆˆˆˆˆ ,' ;' ' -,           •97•
                 ;¯ ;      ;  ;  ', ; ; ,'  ;  ‚¸  ' -,        •••
                 ;   ;     ;       '''''''''''    ``'-,',  ,'
                 ;   ;, -¬;    O   O   O  O   '-',,,,,,,,,,,,
                 ;        ;  O   O     O O    O  ,'     O     ,'
                  ' - - ' `;    O        O  O    O   O    ,'
                       ,-'   O    O    O  O      O    O   ,'
                    ,-' O O  O   O        O        O ,'
                 ,-'   O      O   O   O     O  O     ,'
              ,-'  O    O    O  O   O   O O O   O,-'-,
               ``¬ -,,,,,,,-¬~,~~~~~~~~~--',)  (' -,
                            ',   (',                     ' -,    '-,
                             ',)  (',                        `-,)  ' -,
                              ',    ',                           `-,  ,',-----,
                               ',)   ;                              `\\,- ---'
                           ¸,,,,'‡  (;
                          (¸,,,,,';_'\\ ßy §(V)òó†(–)775 ™
                """;

        System.out.println(computePolkadotScore(art)); // 64
    }
}
