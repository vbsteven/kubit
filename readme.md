# Kubit

Kubit helps you manage multiple kubernetes configs on the same machine, it works very similar to 

Usage
-----

* Add an entry
```sh
$ kubit add development ~/Projects/dev/kubeconfig
```

* List all entries
```sh
$ kubit list
```

* use an entry
```sh
$ kubit use development
```

This will output environment variable export statements to configure your environment for the given entry.
You can eval the output of this command to apply the variables or apply them directly by executing kubit through its shell wrapper (see )

* Remove an entry
```sh
$ kubit rm development
```

* print usage with no opts or the `help` subcommand
```sh
$ kubit help
```


## Installation

### From source

Clone the git repository and build with the following command

```sh
$ ./gradlew installDist
```

This will install the necessary files into build/install/kubit. Add the binary to your path by adding the following line to your `.zshrc` or `.bashrc`

```
export PATH=$PATH:/path/to/kubit_source/build/install/kubit/bin
```

### Enabling automatic export of variables when using `kubit use`

To automatically export the new environment variables when using `kubit use` you
need to run kubit with its shell wrapper. You can add this line to your `.zshrc`
or `.bashrc`

```
eval "$(kubit init)"
```

To improve startup time of your shell you can also run `kubit init` once in your
shell and copy the output into your `.zshrc`/`.bashrc`


Inspiration
-----------

Kubit is inspired by the following projects
* wd (https://github.com/mfaerevaag/wd)[https://github.com/mfaerevaag/wd]
