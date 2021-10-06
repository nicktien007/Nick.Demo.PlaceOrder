package nick.demo.placeorder;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StringTest {
    @Test
    void testStringPadEnd() {
        String result1 = Strings.padEnd("[家電]電動按摩椅", 12, '　');
        String result2 = Strings.padEnd("[電子]行動電源", 12, '　');
        String result3 = Strings.padEnd("[書本]作手回憶錄", 12, '　');

        log.info(result1);
        log.info(result2);
        log.info(result3);
    }
}