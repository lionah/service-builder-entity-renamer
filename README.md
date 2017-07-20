# Service Builder Entity Renamer
Attempts to rename all occurrences (files and its contents) for a particular entity name.

Currently this tool is not "smart". It simply does what I would do by hand programmatically. This was created because the trial period for the applicaiton I used to rename files has lapsed. I don't want to pay money.

So here is what it does:

* It will rename all files with a simple String match from one entity name to another. For example: EntityNameLocalServiceImpl to FooBarLocalServiceImpl.
* It will attempt to rename all variables and class names that match the following sytles:
  * EntityName -> FooBar
  * entityName -> fooBar
  * entity_name -> foo_bar
  * ENTITY_NAME -> FOO_BAR
  * entity-name -> foo-bar
* You can supply it the plural forms of the entities name to avoid odd plural renamings like "Classs" and "Entrys".

# Usage

```
usage: ./run directory from_entity to_entity [from_entity_plural to_entity_plural]
	--disable-content-rename          Renamer will skip over contents of files
	--disable-filename-rename         Renamer will not rename files
```