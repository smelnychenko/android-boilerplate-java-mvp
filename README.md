# Android Skeleton

## Quick start

* Clone the repository and get inside it:
```
git clone https://bitbucket.org/softermiiroman/skeleton.git --origin android-base YourProjectName
cd YourProjectName
```

* Rename the project and package name:
** on Mac, Linux and Windows using git-bash
```
./bin/rename_project YourProjectName your.package.name
```
** on Windows using PowerShell
```
.\bin\rename_project -project YourProjectName -package your.package.name
```

* Create a new remote repository and then execute this to reset the repo and push it:
```
./bin/reset_git https://github.com/yourusername/YourProjectName.git
```

That's it, you can now start developing your own app!
