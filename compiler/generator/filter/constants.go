package filter

import (
	"strings"

	"github.com/Workiva/frugal/compiler/parser"
)

type constantsSpec struct {
	All   *bool    `yaml:"all"`
	Names []string `yaml:"names"`
}

func (ss *constantsSpec) isSpecified(
	s *parser.Constant,
) bool {
	if ss == nil {
		return false
	}

	if ss.All != nil && *ss.All {
		return true
	}

	for _, name := range ss.Names {
		if strings.EqualFold(name, s.Name) {
			return true
		}
	}

	return false
}
