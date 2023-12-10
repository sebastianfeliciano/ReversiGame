package view;

public class HintSystem {
  private boolean hintsEnabledForBlack = false;
  private boolean hintsEnabledForWhite = false;

  public void toggleHintsForBlack() {
    hintsEnabledForBlack = !hintsEnabledForBlack;
  }

  public void toggleHintsForWhite() {
    hintsEnabledForWhite = !hintsEnabledForWhite;
  }

  public boolean areHintsEnabledForBlack() {
    return hintsEnabledForBlack;
  }

  public boolean areHintsEnabledForWhite() {
    return hintsEnabledForWhite;
  }
}
