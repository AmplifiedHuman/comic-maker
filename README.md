# comic-maker

## Quick start

### Requirements

- Java 13+ (13, 14, 15)

JavaFX is bundle in the JAR, so it does not need to be linked.

```bash
java -jar apes.jar
```

## Sprints

### Sprint 1 - Launching the application as a GUI

For this sprint, we designed the wireframe of the application using the JavaFX library. We also integrated maven into
our project so that we can install and run the JavaFX application easily. We decided to split the task into 4 different
stories
(Stage, ScrollPane, ColorPicker and Options Pane) and everyone is responsible for one component. After that, we combined
everything together using a Layout class when everyone is finished with their part. Our final product also included two
sample characters to show that our GUI will in fact work nicely with the character images.

![Sprint 1 Gui](readme-resources/sprint1.png)

### Sprint 2 - Loading characters and adding support for character flipping

For this sprint, we added a dropdown list for choosing characters, users can choose their desired character, and it will
replace the current characters on the stage. Note that, when switching characters, only the orientation will be
preserved and skin color and other attributes will be reset. To select a blank screen, users can just select the **
blank** selection. To flip either the left or right character, users **must click on the character first** to request
focus, and the selected character will be highlighted in blue. After that, the user can choose to flip the selected
character's orientation either to left-to-right or right-to-left.

![Sprint 2](readme-resources/sprint2.png)

We are using a MVC like architecture to structure our application, and it helps us to separate the core logic from the
view layer. Some extra bonuses we added include a focusing effect when a character is clicked, and also dropdown icons
so that the user can preview the character image before selecting it.

In addition to this week's requirement, we have also completed the stories from sprint 3 so **gender, hair color and
skin color changing are all supported**. More documentation details will be included in the next sprint, but the
features are working in our current release.

### Sprint 3 - Changing Gender, Skin Color and Hair Color

For sprint 3, we completed the gender changing requirement by removing the wig, ribbon and lipstick from default female
characters. Our initial colour changing algorithm is not perfect, since some artifacts still remain after we changed
from one colour to another due to **anti-aliasing**. After multiple changes to our algorithm, it currently works almost
perfectly by using BFS and a threshold. Essentially, our algorithm works by doing BFS on the target colour, and it tries
to propagate and find all similar coloured pixels, effectively removing the anti-aliased edges. To prevent duplicate
work, we used a boolean array to check if a pixel has been visited before. A threshold is a nice optimisation since it
prevents the accidental removal of black coloured edges.

Next, our skin colour changing algorithm works very similarly but for male characters, the lips also need to be coloured
to match the skin colour. After some experimenting, we decided to go with a _re-rendering_ approach, where a fresh copy
of the image is modified for each render (changes to gender, hair colour, so on).

Some extra bonuses we added include a custom colour in the color selector which is the default color of the hair/skin to
let the user reset the skin/hair colour. Besides, when a user clicks on a character, the colour changer changes its
current colour to match the character's skin and hair colour.

![Sprint 3](readme-resources/sprint3.png)

> Note that, to achieve the mentioned features, we created additional entities so that it would be easier to serialize
> the characters in later sprints.

In addition to this week's requirement, we have also completed the stories from sprint 4 so **speech & thought bubble ,
narrative bars are all supported**. More documentation details will be included in the next sprint, but the features are
working in our current release.

### Sprint 4 - Adding Dialogue Boxes

We completed the dialogue boxes for the respective characters by our version work slightly different. After consulting
Prof. Tony about our decision, he agreed via email this approach is indeed fine. We feel that the user should be able to
remove the thought/speech bubbles as they wish (ie reset to initial state without speech bubbles at all). So, we
proposed an alternative approach:
When the speech bubble setting is clicked, instead of prompting the user first, we add a empty speech bubble to the
character, and the user can click on that empty speech bubble to enter their desired text in a popup window. So, our
speech bubble setting will act as a hide/show button for the speech bubbles.

Some extra bonuses we added include a warning which limits the number of characters to 100 to ensure that our
application works and displays correctly. Besides, when say a speech bubble already enabled for a character, clicking on
the thought bubble button directly switches the speech bubble style.

![Sprint 4](readme-resources/sprint4.png)

In addition to this week's requirement, we have also completed the stories from sprint 6 so **saving, navigating /
deletion of panels are all supported**. More documentation details will be included in the next sprint, but the features
are working in our current release.

## Team Members

- Chee Guan Tee (Jason) - [@AmplifiedHuman](https://github.com/AmplifiedHuman)
- Choon Wei Tong - [@choonjerald](https://github.com/choonjerald)
- Simonas Ramonas - [@SimonRamone](https://github.com/simonramone)
- Taranpreet Singh - [@Tyrionpreet](https://github.com/tyrionpreet)
