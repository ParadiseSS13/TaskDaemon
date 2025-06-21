# Paradise Task Daemon

## What is it?

Java application designed to automate various backend tasks we have within ParadiseSS13.

It handles:

- Bouncer Restart
- Profiler Daemon Ingest
- Profiler DB Cleanup

### Bouncer Restart

Restarts our "bouncer" server every day. This is the thing that does 2FA, server queue, and region redirection.

### Profiler Daemon Ingest

This takes BYOND Profiler results and split them apart to be indexed at a later date, allowing multiple procs from multiple rounds to easily be visualised alongside eachother over time. An example SS13 codebase implementation can be found at <https://github.com/ParadiseSS13/Paradise/pull/17459>.

This project itself does not provide a data extraction method, but that can be found at <https://github.com/ParadiseSS13/ParadisePublicAPI>.

### Profiler DB Cleanup

Just wipes rows older than 7 days out the profiler table.

## Provided Projects

- `TaskDaemon.Core` - Core application which handles running tasks.
- `TaskDaemon.Database.GameDb` - Database project automatically generated with JOOQ for the `paradise_gamedb` database.
- `TaskDaemon.Database.Profiler` - Database project automatically generated with JOOQ for the `paradise_profiler` database.

Use `mvn package -P update-db` to update DB projects.

## Building

To build, run `mvn package` to generate the JAR file. This will then appear in the `out` directory.

## Running

1. Compile the codebase (See `Building`).
2. Copy the jar and the example `config.toml` in the repository root to a running location.
3. Import the `schemas/profiler_schema.sql` schema to your database server.
4. Setup the configuration as per section labels.
5. Start the application up, it will now run tasks in the background.

## Code Guidelines

- Code must be tested.
  - Good luck.
- Code must adhere to the style guidelines set in `checkstyle.xml`.
  - These are google's guidelines but with minor tweaks for my own needs.

## License

This project is licensed under GPL-3. You can find a copy of this license in the file `LICENSE.MD`.
