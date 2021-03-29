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

## Team Members

- Chee Guan Tee (Jason) - [@AmplifiedHuman](https://github.com/AmplifiedHuman)
- Choon Wei Tong - [@choonjerald](https://github.com/choonjerald)
- Simonas Ramonas - [@SimonRamone](https://github.com/simonramone)
- Taranpreet Singh - [@Tyrionpreet](https://github.com/tyrionpreet)
