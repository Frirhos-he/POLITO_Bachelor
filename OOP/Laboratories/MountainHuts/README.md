<!DOCTYPE html>
<html>

<head lang="en">
  <link href=".css.css" rel="stylesheet" type="text/css">
  <meta charset="utf-8">
  <title>Lab - Mountain huts</title>
  <meta name="revision" content="2021-04-30">
  <meta name="author" content="Marco Torchiano">
</head>

<body>

  <h1>Mountain huts</h1>

  <p>The student shall develop an application for managing the information about mountain huts in a given region.<br>
    Besides the mountain huts, the application must allow to insert the information about altitude ranges and municipalities.<br>
    All classes are inside the package "<b>mountainhuts</b>".</p>

  <h2>R1 - Altitude ranges</h2>

  <p>All interactions are through the class <b>Region</b>. The method <b>getName()</b> of <b>Region</b> returns the name
    of the region as it was specified in the constructor.</p>

  <p>Huts are classified according to their altitude range, and such ranges could be freely defined according to the
    specific needs. Altitude ranges are defined through the method <b>setAltitudeRanges()</b> that gets as a parameter
    an array of strings. Each string describes an altitude range in the format "<i>[minValue]-[maxValue]</i>". E.g., the
    range "<i>0-1000</i>" represents altitudes from 0 to 1,000 meters above sea level with the upper level inclusive. 
    Ranges may be assumed non overlapping.</p>

  <p>The method <b>getAltitudeRange()</b> gets as a parameter an altitude and returns the string describing the range
    that contains the altitude among the ranges defined through <i>setAltitudeRanges()</i>. If no range includes the
    altitude, the method should return the default string "<i>0-INF</i>".</p>

  <h2>R2 - Municipalities and mountain huts</h2>

  <p>Municipalities are defined using the factory method <b>createOrGetMunicipality()</b> that gets as parameters the
    unique name of the municipality, the province, and its altitude. The method returns an object of class
    <b>Municipality</b>. If a municipality with the same name already exists, the method shall return it, ignoring the
    remaining parameters.</p>

  <p>Mountain huts are created using the factory method <b>createOrGetMountainHut()</b> that gets as parameters the
    unique name of the hut, its category, number of beds, and the municipality where it is located. The method
    <b>createOrGetMountainHut()</b> also accept an optional parameter that specifies the altitude of the hut.

    The method returns an object of the class <b>MountainHut</b>. If a hut with the same name already exists, the method
    shall return it, ignoring the remaining parameters.</p>

  <p>The class <i>Municipality</i> and the class <i>MountainHut</i> shall also implement all obvious getters and setters. The
    method <b>getAltitude()</b> in the class <i>MountainHut</i> returns an <a
      href="https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html"><i>Optional</i></a> that is <i>empty</i>
    whether the altitude of the hut was not specified in <i>createOrGetMountainHut()</i>.

  <p>The collections containing the names of municipalities and the names huts are available through the methods <b>getMunicipalities()</b> and <b>getMountainHuts()</b>, respectively.</p>

    <h4>Hints</h4>
    <ul class="hint">
      <li>The class <a href="https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html"><i>Optional</i></a>
        is used to explicitly indicate a value that may not exists.
        The method <i>isPresent()</i> is used to check if a value is available in the optional.<p>

          To create an <i>Optional</i> from a variable that might be <i>null</i> it is possible to use
          <i>Optional.ofNullable()</i> that returns an <i>Optional</i> wrapping the variable, or an empty
          <i>Optional</i> if the variable is <i>null</i>.
    </ul>

    <h2>R3 - Input from CSV</h2>

    <p>The static factory method <b>fromFile()</b> creates an object of class <i>Region</i> using the information stored
      inside a file whose name is passed as an argument. In more details, the method should populate the region with the
      municipalities and the huts described in a <a href="https://en.wikipedia.org/wiki/Comma-separated_values">CSV</a>
      file, structured as follows:</p>

    <style>
      td {
        text-align: center;
      }

      th {
        text-align: left;
      }
    </style>
    <table style="float:none;font-family:sans-serif;">
      <tr>
        <th>#</th>
        <th>Columns</th>
        <th colspan="4">Information</th>
      <tr>
        <th></th>
        <th></th>
        <th><b>Municipality</b></th>
        <th><b>MountainHut</b></th>
      </tr>
      <tr>
        <td>0</td>
        <th><i>Province</i></th>
        <td>&#10003;</td>
        <td></td>
      </tr>
      <tr>
        <td>1</td>
        <th><i>Municipality</i></th>
        <td>&#10003;</td>
        <td></td>
      </tr>
      <tr>
        <td>2</td>
        <th><i>MunicipalityAltitude</i></th>
        <td>&#10003;</td>
        <td></td>
      </tr>
      <tr>
        <td>3</td>
        <th><i>Name</i></th>
        <td></td>
        <td>&#10003;</td>
      </tr>
      <tr>
        <td>4</td>
        <th><i>Altitude</i></th>
        <td></td>
        <td>&#10003;</td>
      </tr>
      <tr>
        <td>5</td>
        <th><i>Category</i></th>
        <td></td>
        <td>&#10003;</td>
      </tr>
      <tr>
        <td>6</td>
        <th><i>BedsNumber</i></th>
        <td></td>
        <td>&#10003;</td>
      </tr>
    </table>

    <p><strong>Note</strong>: the file contains a line for each hut, therefore the information about municipalities may
      be duplicated.</p>

    <p>CSV fields are separated by a semicolon (<i>;</i>). The altitude of a hut is empty if the information is not
      available.</p>

    <p>All data about mountain huts in Piedmont are available in the file:
      <i>mountain_huts.csv</i> <sup>(<a href="#note"> * </a>)</sup>.</p>

    <h4>Hints</h4>
    <ul class="hint">
      <li>To read from file check the fragment of code already available in <b>readData()</b> that reads a file line by
        line, and inserts that information into a list.
        The first line of the file contains the headers, actual data starts from the second line.</li>
    </ul>

    <h2>R4 - Queries</h2>
    <p>The method <b>countMunicipalitiesPerProvince()</b> shall return a map with the name of the province as key and
      the total number of the municipalities of that province as value.</p>

    <p>The method <b>countMountainHutsPerMunicipalityPerProvince()</b> shall return a map with the name of the province
      as key and as value a second map with the name of the municipality as key and the number of mountain huts located
      inside that municipality as value.</p>

    <p>The method <b>countMountainHutsPerAltitudeRange()</b> shall return a map with the altitude range returned by
      <i>getAltitudeRange()</i> as key, and the number of huts in that altitude range (inclusive) as value. When no
      altitude is specified for the hut, do consider the altitude of the municipality.</p>

    <p>The method <b>totalBedsNumberPerProvince()</b> shall return a map with the name of the province as key, and the
      total number of beds available in all huts located in that province as value.</p>

    <p>The method <b>maximumBedsNumberPerAltitudeRange()</b> shall return a map with the altitude range returned by
      <i>getAltitudeRange()</i> as key, and as value the maximum number of beds available in a single hut in that
      altitude range (inclusive). When no altitude is specified for the hut, do consider the altitude of the
      municipality.</p>

    <p>The method <b>municipalityNamesPerCountOfMountainHuts()</b> shall return a map with the number of available huts
      as key, and a list of the municipalities including exactly that number of huts as value. The list should be
      alphabetically sorted.</p>

  <ul class="hint">
  	<li>To implement the queries, usage of Stream API is recommended;
  	    they allow writing more compact and undestandable code,
  	    with respect to explicit iterations on collections and maps</li>
  </ul>

    <footer id="note">
      <sup>(*)</sup>: the file contains a simplified version of the data available on the open data portal of the
      Piedmont region, in particular
      <a href="https://www.dati.piemonte.it/#/catalogodetail/regpie_ckan_ckan2_yucca_sdp_smartdatanet.it_RifugiOpenDa_2296"
        style="display:inline-block;">https://www.dati.piemonte.it/#/catalogodetail/regpie_ckan_ckan2_yucca_sdp_smartdatanet.it_RifugiOpenDa_2296</a>
    </footer>

</body>

</html>
