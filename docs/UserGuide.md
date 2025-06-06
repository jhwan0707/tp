---
layout: page
title: User Guide
---

ConnectEase is a **desktop app for Property Agents to manage contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ConnectEase can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

1. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ConnectEase.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to ConnectEase

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

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

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

* Commands are all in lower case. 

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to ConnectEase

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]… [d/DISTRICT] [k/PRICE] [b/LAND_SIZE] [c/CLIENT_TYPE]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`
* `add n/John p/812312312 e/hisemail@test.com a/address d/5 k/500 b/50 c/s`

Additional Information:
Since it is a common usecase to have multiple clients with the same phone number(many times clients will all communicate through the same agent). ConnectEase will allow multiple contacts with the same phone number.

### Listing all persons : `list`

Shows a list of all persons in ConnectEase

Format: `list`

### Listing all persons with a particular tag : `tag`

Shows a list of all persons in the ConnectEase who have a particular tag.

Format: `tag TAGNAME TAGNAME2`
Examples:
* `tag friends neighbours`

Another way to search for a particular tag is to click on the tag in the person list. This will automatically filter the list to show only the clients with that tag.

Restrictions: Tag must be alphanumeric.


### Editing a person : `edit`

Edits an existing person in ConnectEase

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

## Fuzzy Finding Clients: `f` Command

Quickly search for clients by name, phone, or email with the `f` command. It’s smart, flexible, and forgiving—perfect for when you don’t remember exact details!

### How It Works
- **Command Format**: `f KEYWORD [MORE_KEYWORDS]`
- **What It Searches**: Matches *any* keyword against a client’s name, phone, or email (an "OR" search).
- **Case-Insensitive**: Uppercase, lowercase—it doesn’t care!
- **Matching Rules**:
    - **Name**: Uses *fuzzy matching* (up to 2 typo edits allowed). So, `sara` finds `Sarah` or `Sahara`.
    - **Phone & Email**: Looks for substrings. For example, `123` matches `91234567`, and `gmail` matches `john@gmail.com`.

### Tips
- **Short on Details?** Just type what you know—`f 45` might find a phone number or part of an email.
- **Fuzzy Magic**: Misspellings like `jhon` still find `John` (up to 2 edits).
- **Multiple Keywords**: Add more words to narrow it down, but it’ll still show clients matching *any* keyword.

### Things to Know
- You need at least one keyword (e.g., `f` alone won’t work).

Search smarter, not harder—try `f` today!

### Matching people by valid property details: `match`

Looks for Clients of the opposite valid Client Type which has houses which meets the given requirements.

Format: `match INDEX`

* Clients will be matched if all the minimum requirements are met or better.
* If a given attribute for a user is labelled null, that requirement will be assumed to be valid. Hence, if you put null
* for everything you will get matched to everyone of opposing client type, except for ClientType
* Client Type is the only field that must be filled in
* Client Type must be opposite between the match and to be matched. So if one is a buyer, the other must be a seller, and vice versa
* The District requirement is that the districts are the same
* The Price requirement is that seller must have a lower Price than buyer
* The LandSize requirement is that the seller must have a bigger than or equal LandSize than the buyer

Examples:
* Index 1 has a Client which is of Type BUYER and has requirements price = 5, landsize = 5, district = 5
* Index 2 has a Client of Type SELLER and has requirements of price = 4, landsize = 6, district 5
* Index 3 has a Client of Type SELLER and has requirements of price = 6, landsize = 5, district 5
  ![initial list'](images/match_initial.png)
* There does not exist any other Client
* `match 1` returns Client 2 and his details
  ![match 1'](images/match_1.png)
* `match 2` returns Client 1 and his details
  ![match 2'](images/match_2.png)
* Client 3 is not returned as it is not compatible with the requirements of Client 1 and 2


### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in ConnectEase.
* `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from ConnectEase

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ConnectEase data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

ConnectEase data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ConnectEase will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the ConnectEase to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

### Archiving data files `[coming in v2.0]`

### Add Property `[coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ConnectEase home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**tag** | `tag TAGNAME TAGNAME2`
