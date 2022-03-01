<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![Quality][quality-shield]][quality-url]

<!-- PROJECT LOGO -->
<!--suppress ALL -->
<br />
<p align="center">
  <a href="https://github.com/TamrielNetwork/VitalBack">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">VitalBack</h3>

  <p align="center">
    Teleport back to your last location on Spigot and Paper
    <br />
    <a href="https://github.com/TamrielNetwork/VitalBack"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/TamrielNetwork/VitalBack">View Demo</a>
    ·
    <a href="https://github.com/TamrielNetwork/VitalBack/issues">Report Bug</a>
    ·
    <a href="https://github.com/TamrielNetwork/VitalBack/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#description">Description</a></li>
        <li><a href="#features">Features</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#commands-and-permissions">Commands and Permissions</a></li>
        <li><a href="#configuration">Configuration</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

### Description

VitalBack is a Plugin that gives players the ability to teleport back to their last location.

This plugin is perfect for any server not wanting their players having to remember their last location.

### Features

* Teleport back to last location
* Teleport back to last location on death

### Built With

* [Gradle 7](https://docs.gradle.org/7.4/release-notes.html)
* [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

<!-- GETTING STARTED -->

## Getting Started

To get the plugin running on your server follow these simple steps.

### Commands and Permissions

1. Permission: `vitalback.back`

* Command: `/vitalback`
* Description: Teleport back to last location

2. Permission: `vitalback.back.ondeath`

* Command: `/vitalback`
* Description: Teleport back to last location on death

### Configuration - config.yml

```
# Choose a storage system (mysql or yaml)
storage-system: yaml

mysql:
  host: "localhost"
  port: 3306
  database: vitalback
  username: "vitalback"
  password: ""
  prefix: "server_"
```

### Configuration - messages.yml

```
player-only: "&cThis command can only be executed by players!"
invalid-option: "&cInvalid option!"
no-perms: "&7You don't have enough permissions!"
cmd: "cmd"
```

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/TamrielNetwork/VitalBack/issues) for a list of proposed features (and known
issues).

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<!-- CONTACT -->

## Contact

Leopold Meinel - [@TamrielN](https://twitter.com/TamrielN) - Twitter

Leopold Meinel - [contact@tamriel.me](mailto:contact@tamriel.me) - eMail

Project Link - [VitalBack](https://github.com/TamrielNetwork/VitalBack) - GitHub

<!-- ACKNOWLEDGEMENTS -->

### Acknowledgements

* [README.md - othneildrew](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors-anon/TamrielNetwork/VitalBack?style=for-the-badge

[contributors-url]: https://github.com/TamrielNetwork/VitalBack/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/TamrielNetwork/VitalBack?label=Forks&style=for-the-badge

[forks-url]: https://github.com/TamrielNetwork/VitalBack/network/members

[stars-shield]: https://img.shields.io/github/stars/TamrielNetwork/VitalBack?style=for-the-badge

[stars-url]: https://github.com/TamrielNetwork/VitalBack/stargazers

[issues-shield]: https://img.shields.io/github/issues/TamrielNetwork/VitalBack?style=for-the-badge

[issues-url]: https://github.com/TamrielNetwork/VitalBack/issues

[license-shield]: https://img.shields.io/github/license/TamrielNetwork/VitalBack?style=for-the-badge

[license-url]: https://github.com/TamrielNetwork/VitalBack/blob/main/LICENSE

[quality-shield]: https://img.shields.io/codefactor/grade/github/TamrielNetwork/VitalBack?style=for-the-badge

[quality-url]: https://www.codefactor.io/repository/github/TamrielNetwork/VitalBack
