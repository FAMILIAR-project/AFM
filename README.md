AFM
===

Attributed feature model (API, reasoners, importers/exporters, etc.) 

Three different projects can be found in this repo.

First, the AttributedModel project, this is the main SDK for MOTIV and for the current attributes operations. The VM parsers are in this project, which introduces a dependency with the VM project FAMILIAR/VM-LANGUAGE

Second, the pair-wise choco reasoner. This packages contains two reasoning engines, one for the normal operations and a second one for the pair-wise ones, which are more complex. It depends in the AttributedModel

Third, the AFMtest, this is a simple project that depends in the others and performs a set of tests and operations over the models. It depends in both previous projects, the AttributedModel and the choco reasoner.
