# Tugas Besar MKEPL - Implementasi Pipeline CI/CD GitHub Actions

## Deskripsi Proyek
Repositori ini menggunakan proyek Tugas Besar DPBO `tubesv2`, yaitu aplikasi Java berbasis CLI untuk pengelolaan booking tempat dan sewa alat camping/hiking. Fitur utama meliputi login, registrasi, manajemen profile, booking, deposit, laporan kerusakan, kelola alat, dan logbook aksi.

## Arsitektur Pipeline CI/CD
Pipeline dibuat menggunakan GitHub Actions dengan pendekatan Continuous Delivery. Branch `dev` digunakan untuk pengembangan dan validasi CI/CT/Inspection, sedangkan branch `main` digunakan untuk menjalankan pipeline penuh sampai deployment staging dan release production dengan approval manual.

| Tahap | Job GitHub Actions | Trigger | Perintah/Tools | Keluaran |
|---|---|---|---|---|
| Continuous Integration | `build` | push ke `dev`/`main`, pull request ke `main` | `mvn -B clean package -DskipTests` | Build artifact `.jar` |
| Continuous Testing | `test` | setelah build berhasil | JUnit 5, Maven Surefire, `mvn -B test` | Laporan test di `target/surefire-reports` |
| Continuous Inspection | `inspect` | setelah test berhasil | SonarCloud, JaCoCo, Quality Gate | Pipeline gagal jika quality gate gagal |
| Continuous Delivery | `deploy-staging` | push ke `main` setelah quality gate lolos | GitHub Actions artifact | Artifact staging |
| Production Release | `release-production` | setelah staging berhasil | GitHub Environment `production` + approval manual | GitHub Release berisi file `.jar` |

## Tools dan Teknologi
| Kebutuhan | Tools |
|---|---|
| Bahasa pemrograman | Java 17 |
| Build tool | Apache Maven |
| Unit testing | JUnit 5 |
| Test report | Maven Surefire |
| Coverage | JaCoCo |
| Static analysis | SonarCloud |
| CI/CD | GitHub Actions |
| Artifact/release | GitHub Actions Artifact dan GitHub Release |

## Strategi Branching
1. `dev`: branch kerja untuk pengembangan fitur, perbaikan bug, dan validasi build/test/inspection.
2. `main`: branch stabil. Merge dari `dev` ke `main` hanya dilakukan setelah pipeline sukses.
3. Pull request ke `main` wajib melewati status checks: build, test, dan inspect.
4. Deployment staging berjalan otomatis pada branch `main`.
5. Release production menggunakan approval manual melalui GitHub Environment `production`.

## Environment Variable dan Secrets
Tambahkan secrets berikut di GitHub repository melalui menu **Settings > Secrets and variables > Actions**:

| Secret | Fungsi |
|---|---|
| `SONAR_TOKEN` | Token autentikasi SonarCloud |
| `SONAR_ORGANIZATION` | Organization key SonarCloud |
| `SONAR_PROJECT_KEY` | Project key SonarCloud |

`GITHUB_TOKEN` tidak perlu dibuat manual karena otomatis tersedia dari GitHub Actions.

## Cara Menjalankan Lokal
```bash
cd tubesv2
mvn clean package
mvn test
mvn exec:java
```

## Pembagian Tugas
| Nama | NIM | Tanggung Jawab |
|---|---|---|
| Onesiforus Rade Putra | 1302210105 | Setup pipeline CI/CD, Continuous Testing, Continuous Inspection,Continuous Delivery, unit test, SonarCloud, README, dokumentasi, dan demonstrasi |

## Skenario Demonstrasi Video
### Kasus Sukses
1. Push perubahan ke branch `dev`.
2. Tunjukkan job `build`, `test`, dan `inspect` sukses.
3. Merge pull request ke `main`.
4. Tunjukkan staging deployment berhasil.
5. Tunjukkan release production menunggu approval manual.

### Kasus Gagal
1. Ubah salah satu assertion unit test agar gagal, misalnya expected saldo salah.
2. Push ke branch `dev`.
3. Tunjukkan job `test` gagal dan pipeline berhenti.
4. Jelaskan bahwa Continuous Testing menolak perubahan karena test tidak lulus.
