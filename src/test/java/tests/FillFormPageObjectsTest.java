package tests;

import org.junit.jupiter.api.Test;
import pages.FormPage;

public class FillFormPageObjectsTest {
    FormPage formPage = new FormPage();

    @Test
    public void fillFormTest(){
        formPage.openPage();
        formPage.fillForm();
        formPage.checkResult();
    }
}
