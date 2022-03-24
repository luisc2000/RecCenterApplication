[1;33mWe periodically need to update the local cache. Please run:[0m

  $ sdk update


[1;31mInvalid command: kill-server[0m


Usage: sdk <command> [candidate] [version]
       sdk offline <enable|disable>

   commands:
       install   or i    <candidate> [version] [local-path]
       uninstall or rm   <candidate> <version>
       list      or ls   [candidate]
       use       or u    <candidate> <version>
       config
       default   or d    <candidate> [version]
       home      or h    <candidate> <version>
       env       or e    [init|install|clear]
       current   or c    [candidate]
       upgrade   or ug   [candidate]
       version   or v
       broadcast or b
       help
       offline           [enable|disable]
       selfupdate        [force]
       update
       flush             [archives|tmp|broadcast|version]

   candidate  :  the SDK to install: groovy, scala, grails, gradle, kotlin, etc.
                 use list command for comprehensive list of candidates
                 eg: $ sdk list
   version    :  where optional, defaults to latest stable if not provided
                 eg: $ sdk install groovy
   local-path :  optional path to an existing local installation
                 eg: $ sdk install groovy 2.4.13-local /opt/groovy-2.4.13



[1;33mATTENTION: A new version of SDKMAN is available...[0m

The current version is 5.14.1, but you have 5.12.2.

[1;33mWould you like to upgrade now? (Y/n): [0m