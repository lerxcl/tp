---
layout: page
title: User Guide
---

Lifebook is a **desktop application intended for University students to  manage contact details, assignments, projects,
and module details.** Lifebook supports Command Line Interface (CLI) for efficient contacts and tasks management while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `lifebook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Lifebook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list contact`** : Lists all contacts.

   * **`add contact`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the contact list.

   * **`add todo`**`desc/homework date/12-12-2020 time/2359`: Adds a todo with a description `homework` that is due by `12 December 2020, 2359`.

   * **`link meeting`**`desc/Annual meeting url/https://nus-sg.zoom.us/j/98401234359?pwd=eG9HU1FJRDdsVHRaYkFUTC95L0abcedf todo i/2 date/20/1/2020 time/2359` : Add a zoom meeting titled 'annual meeting' at 2359, 20 Jan 2020 to the app.

   * **`delete contact`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear contact`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters for contacts can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

<h2> Contact</h2>

### Adding a person: `add contact`

Adds a person to the contact list.

Format: `add  contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add contact n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add contact n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `list contact`

Shows a list of all persons in the contact list.

Format: `list contact`

### Editing a person : `edit contact`

Edits an existing person in the contact list.

Format: `edit contact INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit contact 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit contact 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Showing persons with a specific tag: `show`
Shows persons whose tags match the tag being searched.

Format: `show t/TAG`
* The search is case-insensitive. e.g `friends` will match `Friends`
* Only full words will be matched. e.g. `friend` will not match `friends`
* Persons whose one of the tag(s) matches the tag searched will be returned.
e.g. Hans with tag `friends` and `colleagues` will be returned when tag `friends` is searched.

Examples:
* `show t/colleagues`
* `show t/friends`

### Locating persons by name: `find contact`

Finds persons whose names contain any of the given keywords.

Format: `find contact KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find contact John` returns `john` and `John Doe`
* `find contact alex david` returns `Alex Yeoh`, `David Li`<br>

  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `delete contact`

Deletes the specified person from the contact list.

Format: `delete contact INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list contact` followed by `delete contact 2` deletes the 2nd person in the contact list.
* `find contact Betsy` followed by `delete contact 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear contact`

Clears all entries from the contact list.

Format: `clear contact`

<h2> Todo </h2>

### Adding To Dos : `add todo`
Adds a To Do to the TodoList of LifeBook.

Format: `add todo desc/DESCRIPTION date/DATE time/TIME`

* `DATE` must be specified in the format of DD-MM-YYYY
* `TIME` must be speficied in the format of HHmm using 24 hour time

Examples:

* `add todo desc/cs2101 Oral Presentation reflection date/08-08-2020 time/2359`
* `add todo desc/user guide draft date/09-08-2020 time/2300`

### View all To Dos : `list todo`
Shows a list of all To Do's in the TodoList.

Format: `list todo`

### Removing To Dos : `delete todo`
Removes a To Do from the TodoList of LifeBook.

Format: `delete todo INDEX`

* Removes the To Do at the specified `INDEX`.
* The index refers to the index number shown in the displayed TodoList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list todo` followed by `delete todo 3`  removes the 3rd To Do from the To Do list.

### View details of a specific To Do: `show todo`
Shows all details of a specific To Do on the TodoList.

Format: `show todo i/INDEX`

* Shows all details of the To Do at the specified `INDEX`.
* The index refers to the index number shown in the displayed TodoList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list todo` followed by `show todo i/3`  shows all details of the 3rd To Do on the To Do list.

### Mark To Do as complete: `done`
Marks a To Do on the TodoList as complete.

Format: `done INDEX`

* Marks the To Do at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed TodoList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list todo` followed by `done todo 3`  marks the 3rd To Do on the To Do list as complete.

<h2> Event </h2>

### Adding Events : `add event`
Adds an Event o to the EventList of LifeBook.

Format: `add event desc/DESCRIPTION startdate/DATE starttime/TIME enddate/DATE endtime/TIME`

* `DATE` must be specified in the format of DD-MM-YYYY
* `TIME` must be speficied in the format of HHmm using 24 hour time

Examples:

* `add event desc/Party with friends startdate/08-08-2020 starttime/2359 enddate/10-08-2020 endtime/2359`
* `add event desc/Singapore Fintech Festival startdate/09-08-2020 starttime/1000 enddate/12-08-2020 endtime/2359`

### View all Events : `list event`
Shows a list of all Events in the EventList.

Format: `list event`

### Removing Events : `delete event`
Removes an Event from the EventList of LifeBook.

Format: `delete event INDEX`

* Removes the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed EventList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list event` followed by `delete event 3`  removes the 3rd Event from the Event list.

### View details of a specific Event: `show event`
Shows all details of a specific Event on the EventList.

Format: `show event INDEX`

* Shows all details of the Event at the specified `INDEX`.
* The index refers to the index number shown in the displayed EventList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list event` followed by `show event 3`  shows all details of the 3rd Event on the Event list.

### Mark To Do as complete: `done`
Marks an Event on the EventList as complete.

Format: `done INDEX`

* Marks the Event at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed EventList.
* The index **must be a positive integer** 1, 2, 3, …​

Example:
* `list event` followed by `done event 3`  marks the 3rd Event on the Event list as complete.

<h2> Others </h2>

### Listing all contacts and tasks : `list all`

Shows a list of all contacts, events, and to-dos in the Lifebook.

Format: `list all`

### Adding a zoom meeting: `link meeting`

Adds a zoom link for a task.

Format:
* `link meeting desc/DESCRIPTION url/MEETING LINK i/INDEX_OF_TODO date/DD-MM-YYYY time/HHmm`

Examples:
* `link meeting desc/Job interview url/https://nus-sg.zoom.us/j/98221234359?pwd=eG9HU1FJRDdsVHRaYk2UTC95L0abcedf i/2 date/22-9-2020 time/1400`

### Adding a collaborative folder link: `link doc`

Adds a collaborative link (Google Drive, GitHub, Trello, and others)

Format:
* `link doc desc/DESCRIPTION url/LINK i/INDEX_OF_TODO` 

Examples:
* `link doc desc/CS2103T Team Project url/https://drive.google.com/drive/folders/1zoUz1JpAgynIkfacr0asqV9A4kh i/2`

### Filter deadlines : `itemsDue`

User can search todos/meetings due by/before a certain date/time.

Outputs a list of results.

* Due by
    `itemsDueBy date/DD-MM-YYYY time/HHmm`

    `DD-MM-YYYY`: States the Day, Month, Year.

    `HHmm`: States the time in 24 hours.

    Example: `itemsDueBy date/12-12-2020 time/2359`
    It will output a list of todos/meetings that are due specifically **on** 12th December 2020, 2359.

* Due before
    `itemsDueBefore date/DD-MM-YYYY time/HHmm`

    `DD-MM-YYYY`: States the Day, Month, Year.

    `HHmm`: States the time in 24 hours.

    Example: `itemsDueBefore date/12-12-2020 time/2359`
    It will output a list of todos/meetings that are due specifically **before** 12th December 2020, 2359.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Lifebook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files

This feature will be implemented in the next iteration (v1.2).

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Lifebook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Contact** | `add contact n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add contact n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear Contacts** | `clear contact`
**Delete Contact** | `delete contact INDEX`<br> e.g., `delete contact 3`
**Edit Contact** | `edit contact INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]`<br> e.g.,`edit contact 2 n/James Lee e/jameslee@example.com`
**Find Contact** | `find contact KEYWORD [MORE_KEYWORDS]`<br> e.g., `find contact James Jake`
**List (Contacts and Tasks)** | `list all`
**List Contacts** | `list contact`
**Help** | `help`
**Add To Do** | `add todo desc/DESCRIPTION date/DATE time/TIME` <br> e.g., `add todo desc/finish user guide draft date/09/08/2020 time/2300`
**List To Dos** | `list todo`
**Remove To Do** | `delete todo INDEX` <br> e.g., `delete todo 3`
**Show To Do** | `show todo INDEX` <br> e.g., `show todo 3`
**Add Event** | `add event desc/DESCRIPTION startdate/DATE starttime/TIME enddate/DATE endtime/TIME` <br> e.g., `add event desc/meeting startdate/12-12-2020 starttime/1000 enddate/12-12-2020 endtime/1130`
**List Events** | `list event`
**Remove Event** | `delete event INDEX` <br> e.g., `delete event 3`
**Show Event** | `show event INDEX` <br> e.g., `show event 3`
**Show** | `show t/TAG` <br> e.g., `show t/friends`
**Mark To Do/Event as Complete** | `done INDEX` <br> e.g., `done 5`
**Find tasks due by** | `itemsDueBy date/DD-MM-YYYY time/HHmm` <br> e.g. `itemsDueBy date/12-12-2020 time/2359`
**Find tasks due before** |  `itemsDueBefore date/DD-MM-YYYY time/HHmm` <br> e.g. `itemsDueBefore date/12-12-2020 time/2359`
**Link meeting** | `link meeting desc/DESCRIPTION url/LINK todo/event i/INDEX_OF_TASK` <br> e.g.,`link meeting desc/Job interview url/https://nus-sg.zoom.us/j/98221234359?pwd=eG9HU1FJRDdsVHRaYk2UTC95L0abcedf event i/2 date/22/9/2020 time/1400`
**Link doc** | `link doc desc/DESCRIPTION url/LINK todo/event i/INDEX_OF_TASK` <br> e.g.,`link doc desc/CS2103T Team Project url/https://drive.google.com/drive/folders/1zoIkfacr0asqV9A4kh todo i/2`
