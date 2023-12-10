package ExtraCreditTests;

import org.junit.Assert;
import org.junit.Test;

import view.HintSystem;

public class ExampleHintSystemTests {

  @Test
  public void toggleHintsForBlackTester(){
    HintSystem hintSystem = new HintSystem();
    boolean hintsAreNot = hintSystem.areHintsEnabledForBlack();
    Assert.assertFalse(hintsAreNot);
    hintSystem.toggleHintsForBlack();
    boolean hintsAre = hintSystem.areHintsEnabledForBlack();
    Assert.assertTrue(hintsAre);
  }

  @Test
  public void toggleHintsForWhiteTester(){
    HintSystem hintSystem = new HintSystem();
    boolean hintsAreNot = hintSystem.areHintsEnabledForWhite();
    Assert.assertFalse(hintsAreNot);
    hintSystem.toggleHintsForWhite();
    boolean hintsAre = hintSystem.areHintsEnabledForWhite();
    Assert.assertTrue(hintsAre);
  }


}
